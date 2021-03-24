package org.study.PizzaDelivery.controller;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.study.PizzaDelivery.config.EmailConfig;
import org.study.PizzaDelivery.config.TestApplicationConfig;
import org.study.PizzaDelivery.config.WebConfig;
import org.study.PizzaDelivery.config.WebSecurityConfig;
import org.study.PizzaDelivery.model.Role;
import org.study.PizzaDelivery.model.User;
import org.study.PizzaDelivery.repository.RoleRepository;
import org.study.PizzaDelivery.repository.UserRepository;
import org.study.PizzaDelivery.service.UserService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {WebConfig.class, TestApplicationConfig.class,
        EmailConfig.class, WebSecurityConfig.class})
public class MainControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private UserDetails userForTest;


    private Role userRole;

    private Role adminRole;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        if (roleRepository.findByName(roleUser.getName()) == null &&
                roleRepository.findByName(roleAdmin.getName()) == null) {
            roleRepository.save(roleUser);
            roleRepository.save(roleAdmin);
        }

        adminRole = roleRepository.findByName("ROLE_ADMIN");
        userRole = roleRepository.findByName("ROLE_USER");

        User user = new User();
        user.setUsername("UserForTest");
        String pass = "Test12348";
        user.setPassword(pass);
        user.setPasswordConfirm(pass);
        user.setMail("Test@gmail.com");
        user.setPhoneNumber("+375291111111");

        userService.saveUser(user);
        this.userForTest = userService.loadUserByUsername(user.getUsername());
    }

    @AfterEach
    public void resetDb() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void getMainPageWithAuthUserTest() throws Exception {

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(userForTest.getUsername());
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/")).andExpect(status().isOk());

    }

    @Test
    void getMainPageWithoutAuthUserTest() throws Exception {

        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("anonymousUser");
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/")).andExpect(status().isOk());

    }

    @Test
    void getConstructorPageWithoutAuthUserTest() throws Exception {

        Authentication authentication = mock(Authentication.class);

        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
       // when(securityContext.getAuthentication().getPrincipal()).thenReturn();

        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/constructor")).andExpect(status().isOk());

    }

}
