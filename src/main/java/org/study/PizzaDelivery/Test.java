package org.study.PizzaDelivery;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.study.PizzaDelivery.config.DataServiceConfig;
import org.study.PizzaDelivery.data.model.Base;
import org.study.PizzaDelivery.data.model.Basket;
import org.study.PizzaDelivery.data.model.BasketItem;
import org.study.PizzaDelivery.data.model.User;
import org.study.PizzaDelivery.data.repository.*;
import org.study.PizzaDelivery.data.service.*;


public class Test {
//INSERT INTO public.t_role(id, name)
//        VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');
//INSERT INTO public.t_user_roles(user_id, roles_id)
//        VALUES (1, 2);


    public static void main(String[] args) {


        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DataServiceConfig.class);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("admin@123"));

       // BCryptPasswordEncoder encoder = context.getBean("passwordEncoder", BCryptPasswordEncoder.class);
        ProductRepository pr = context.getBean("productRepository", ProductRepository.class);
        ProductService ps = context.getBean("productService", ProductService.class);
        CategoryService cs = context.getBean("categoryService", CategoryService.class);
        CategoryRepository cr = context.getBean("categoryRepository", CategoryRepository.class);
        BaseRepository br = context.getBean("baseRepository", BaseRepository.class);
        BaseService bs = context.getBean("baseService", BaseService.class);
        UserService us = context.getBean("userService", UserService.class);
        UserRepository ur = context.getBean("userRepository", UserRepository.class);
        OrderRepository or = context.getBean("orderRepository", OrderRepository.class);
        OrderService os = context.getBean("orderService", OrderService.class);
        OrderItemRepository oIr = context.getBean("orderItemRepository", OrderItemRepository.class);
IngredientRepository ir = context.getBean("ingredientRepository", IngredientRepository.class);
BasketRepository baskr = context.getBean("basketRepository", BasketRepository.class);
        BasketService baskS = context.getBean("basketService", BasketService.class);
        BasketItemRepository bir  = context.getBean("basketItemRepository", BasketItemRepository.class);
        BasketItemService bis = context.getBean("basketItemService", BasketItemService.class);

        System.out.println(ps.findDistinctTopByName("Majorino"));

        //System.out.println(pr.findDistinctNameByCategoryId());

/*       Base b1 =  ps.findOne(41L).getBase();

        Base b2 =  bs.findById((short) 1);
        System.out.println(ps.findOne(41L).getBase());
        System.out.println(bs.findById((short) 1));
        System.out.println(b1.equals(b2));*/


//        System.out.println(bir.findAllByBasketId(5L));
//        System.out.println(baskS.calculatePrice(5L));

       // System.out.println(bir.findById(10L).get().toString());
        //BasketItem item = bir.findById(10L).get();
     //   System.out.println("1");
     //   pr.deleteById(1L);
      //  bir.deleteByItemId(4L);
     //   System.out.println("2");
       // bis.deleteItem();
       // System.out.println(baskS.findActiveByUserID(1L).getBasketItems());

//        System.out.println(
//User user = ur.findById(1L).get();
////
//baskr.save(new Basket(true, user));
//        baskr.save(new Basket(false, user));


       // System.out.println(or.findAllByStatusNotPaid());
      //  System.out.println(or.findAllByStatus(Status.NOT_PAID));
       // System.out.println(baskr.findBasketByUser_IdAndActiveIsTrue(1L, true));
/* System.out.println(ir.findAll());*/
/*ir.save(new Ingredient("Mozarella", 1.5, Type.CHEESE));
        ir.save(new Ingredient("Pork", 3.5, Type.MEAT));
        ir.save(new Ingredient("Beef", 4.0, Type.MEAT));
        ir.save(new Ingredient("Tomato", 1.5, Type.VEGETABLE));
        ir.save(new Ingredient("Onion", 1.0, Type.VEGETABLE));
        ir.save(new Ingredient("Cheddar", 2.0, Type.CHEESE));*/
        //        User user = us.findUserById(1L);
//        Order o = new Order(user, "+375291051607", 20.6, TypeOfPayment.CASH, "Сделайте красиво Рельно, шоб пушка было вооооще прям ммммммммммммммммммммммммммммммммммм.");
//        or.save(o);
/*        Order o = or.findById(1L);
        oIr.save(new OrderItem(o,
                pr.findById(1L),pr.findById(1L).getPrice(),"Onion"));
        System.out.println(or.findAll());
        System.out.println(TypeOfPayment.CASH.toString());*/


      //  us.makeAdmin(1L);
//
//        dr.save(new Dough("Классика", 2.5, "большое"));
//cr.save(new Category("Любимые"));
//cr.save(new Category("Классика"));
//Category c = cs.findOne((short)1);
//Dough d= dr.findById((short)1 );
//Pizza p = Pizza.builder().dough(d).category(c).price(6.5).name(c.getName()).isVegan(true).describe("test").build();
//pr.save(p);
      //  BCryptPasswordEncoder crypt = context.getBean("passwordEncoder", BCryptPasswordEncoder.class);
    //    System.out.println(crypt.encode("admin@123"));
//        System.out.println(encoder.encode("75397539"));

//        Base b = new Base("Большая", 2.5);
//        Base b1 = new Base("Средняя", 2.5);
//        Base b2 = new Base("Маленькая", 2.5);
//        Base b3 = new Base("Ультратонкая", 2.5);
//        Base b4 = new Base("Пита", 1.5);

       /* br.save(b);
        br.save(b1);
        br.save(b2);*/
/*        br.save(b3);
        br.save(b4);*/
     //   Product p = Product.builder().name("Churesco").describe("Italian pizza").base(dr.findById((short) 1)).category(cs.findOne((short) 2)).build();
    //    Product p2 = Product.builder().name("Celimesto").describe("Italian pizza").base(dr.findById((short) 1)).category(cs.findOne((short) 1)).build();
    //    Product p3 = Product.builder().name("Muchacha").describe("Dangerous pizza").base(dr.findById((short) 1)).category(cs.findOne((short) 1)).build();
//        Pizza p4 = Pizza.builder().name("Seledka").describe("Russian pizza pizza").dough(dr.findById((short) 3)).category(cs.findOne((short) 2)).build();
  //      pr.save(p2);
   //     pr.save(p3);
      //  pr.save(p);
      //  pr.save(p);
//        System.out.println(ps.findByCategoryName("classic"));



//        System.err.println(ps.findByCategoryName("classic"));
//        System.err.println(ps.findByCategoryName("Classic"));

    //    System.err.println("category 1" + ps.findAllByCategoryId((short) 1));
//        System.err.println("category 2" + ps.findAllByCategoryId((short) 2));
//        System.err.println("category 3" +   ps.findAllByCategoryId((short) 3));
//        System.err.println("category 4" + ps.findAllByCategoryId((short) 4));
//        System.err.println("category 5" + ps.findAllByCategoryId((short) 5));







//        cr.save(new Category("Classic"));
//        cr.save(new Category("Lovely"));
//        cr.save(new Category("Premium"));
//
//        dr.save(new Dough("small thin", 1.5, "small"));
//        dr.save(new Dough("medium thin", 2.5, "medium"));
//        dr.save(new Dough("small thin", 1.5, "small"));
//

//        Pizza p2 = Pizza.builder().name("Farm").describe("Not existed pizza").dough(dr.findById((short) 2)).category(cs.findOne((short) 2)).build();
//        Pizza p3 = Pizza.builder().name("Country").describe("Not existed pizza").dough(dr.findById((short) 3)).category(cs.findOne((short) 3)).build();

//        pr.save(p2);
//        pr.save(p3);
////        System.out.println(categoryService.findOne((short) 1));
////        System.out.println(pr.findById((short) 1));
////        System.out.println(pr.findById((short) 2));
//
//        System.out.println(ps.findAllByCategoryId((short) 1));
//        System.out.println(ps.findAllByCategoryId((short) 2));
    }
}

//    AnnotationConfigApplicationContext context =
//            new AnnotationConfigApplicationContext(DataServiceConfig.class);
//
//    PizzaRepository pr = context.getBean("pizzaRepository", PizzaRepository.class);
//    CategoryService categoryService = context.getBean("categoryService", CategoryService.class);
//
//    //  pr.save(new Category("lovely"));
//    //    pr.save(new Category("classic"));
//    Pizza p = Pizza.builder().name("Love").describe("Not existed pizza").category(categoryService.findOne((short) 1)).build();
//        pr.save(p);
//                System.out.println(categoryService.findOne((short) 1));
////        System.out.println(pr.findById((short) 1));
////        System.out.println(pr.findById((short) 2));