package org.study.PizzaDelivery.controller;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.study.PizzaDelivery.config.*;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.repository.UserRepository;
import org.study.PizzaDelivery.service.UserService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {WebConfig.class, TestApplicationConfig.class,
        EmailConfig.class, WebSecurityConfig.class})
public class CategoryControllerTest {

    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;

    private UserDetails userForTest;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        User user = new User();

        user.setUsername("UserForTest");
        String pass = "Test12348";
        user.setPassword(pass);
        user.setMail("Test@gmail.com");
        user.setPhoneNumber("+375291111111");
        System.err.println("UST1: " + user);
        userService.saveUser(user);


        System.err.println("UST2: " + user);
        this.userForTest = userRepository.findById(1L).get();

    }

    @AfterEach
    public void resetDb() {
        userRepository.deleteAll();
    }

    @Test
    void getAccount() throws Exception {
       User user= Mockito.mock(User.class);

        Authentication authentication = Mockito.mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);

       when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(userForTest.getUsername());
        when(user.getPasswordConfirm());
        SecurityContextHolder.setContext(securityContext);


        mockMvc.perform(get("/")).andExpect(status().isOk());

    }
}
