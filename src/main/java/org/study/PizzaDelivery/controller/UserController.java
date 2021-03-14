package org.study.PizzaDelivery.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.study.PizzaDelivery.enums.TypeOfPayment;
import org.study.PizzaDelivery.model.Basket;
import org.study.PizzaDelivery.model.Order;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.service.BasketItemService;
import org.study.PizzaDelivery.service.BasketService;
import org.study.PizzaDelivery.service.EmailService;
import org.study.PizzaDelivery.service.OrderService;
import org.study.PizzaDelivery.utils.FileChecker;
import org.study.PizzaDelivery.utils.Formatter;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketItemService basketItemService;

    @Autowired
    private Formatter formatter;

    @Autowired
    private EmailService emailService;

    @Autowired
    ServletContext context;

    @Autowired
    FileChecker fileChecker;


    @GetMapping
    public String account(@ModelAttribute User user, Model model) {
        logger.info("GET request /user");
        model.addAttribute("userOrders", orderService.findOrdersByUserId(user.getId()));

        return "user/ordersHistory";
    }

    @GetMapping("/basket")
    @Transactional
    public String basket(@ModelAttribute User user, Model model) {
        logger.info("GET request /user/basket");

        Basket basket = basketService.findActiveByUserID(user.getId());
        model.addAttribute("basket", basket);
        model.addAttribute("basketSum", basketService.calculatePrice(basket.getId()));
        model.addAttribute("typesOfPayment", TypeOfPayment.values());

        return "user/basket";
    }


    @PostMapping("/basket")
    public String basketActivity(@ModelAttribute User user,
                                 @RequestParam(defaultValue = "") Long itemId,
                                 @RequestParam(defaultValue = "") String action,
                                 @RequestParam(defaultValue = "") String comment,
                                 @RequestParam(defaultValue = "") String orderPhoneNumber,
                                 @RequestParam(defaultValue = "") Double change,
                                 @RequestParam(defaultValue = "") TypeOfPayment typeOfPayment,
                                 Model model) {
        logger.info("POST request /user/basket" +
                "[user: " + user +
                ", itemId: " + itemId +
                ", orderPhoneNumber: " + orderPhoneNumber +
                ", comment: " + comment +
                ", typeOfPayment: " + typeOfPayment +
                ", action: " + action +
                ", change:" + change + "]");

        if (action.equals("deleteItem")) {
            basketItemService.deleteItem(itemId);
        }
        if (action.equals("clear")) {
            basketService.clearBasket(basketService.findActiveByUserID(user.getId()));
        }
        if (action.equals("submit")) {
            if (orderPhoneNumber.equals("") ||
                    !Pattern.matches(formatter.getPhoneRegEx(), orderPhoneNumber)) {
                logger.error("Errors in form. Phone number not specified.");
                model.addAttribute("phoneError", "user.phoneNumber.empty");
                return this.basket(user, model);
            }
            if (change != null) {
                comment = formatter.commentWithChangeFormatter(comment, change);
            }

            Order notPaidOrder = orderService.addOrder(user, orderPhoneNumber, comment, typeOfPayment);
            emailService.sendOrderInfoMessage(user, orderService.findLastOrderOfUserByUserId(user.getId()));

            if (typeOfPayment.equals(TypeOfPayment.ONLINE)) {
                model.addAttribute("orderId", notPaidOrder.getId());
                model.addAttribute("orderSum", notPaidOrder.getPrice());
                return "onlinePayment";
            }

            return "redirect:/user";
        }

        return "redirect:/user/basket";
    }

    @GetMapping("/editUser")
    public String fileUploadForm(Model model) {
        return "user/editUser";
    }

    @PostMapping("/editUser")
    public ResponseEntity uploadUserPhoto(@RequestParam("file") MultipartFile file)
            throws IOException {

        if (fileChecker.jpgExtensionCheck(file) || fileChecker.pngExtensionCheck(file)) {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(context.getRealPath("") + File.separator
                                    + "resources/images/usersPicture" + File.separator,
                                   file.getOriginalFilename())));
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        }else{
            return new ResponseEntity<>("Invalid file.", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>("File Uploaded Successfully.",HttpStatus.OK);
    }
}
