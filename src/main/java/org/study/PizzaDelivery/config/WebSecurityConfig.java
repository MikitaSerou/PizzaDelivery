package org.study.PizzaDelivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Configuration
@EnableWebSecurity
@PropertySource({"classpath:application.properties"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    UserDetailsService userService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                //Access only for unregistered:
                .antMatchers("/registration").not().fullyAuthenticated()
                //Only for ADMIN role:
                .antMatchers("/admin/**").hasRole("ADMIN")
                //Access for user:
                .antMatchers("/promotions", "/user/**", "/constructor", "/onlinePayment").hasRole("USER")
                //Access for all:
                .antMatchers("/", "/**", "/promotions", "/resources/**", "/category/**", "/login")
                .permitAll()
                //All other pages require authentication:
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //Redirect to main page after successful sign in:
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout().deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .permitAll()
                .logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .rememberMeCookieName("remember-me")
                .alwaysRemember(true)
                .useSecureCookie(true)
                .key("uniqueAndSecret");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        //Default admin in memory
        auth.inMemoryAuthentication().withUser(Objects.requireNonNull(env.getProperty("admin.login")))
                .password(passwordEncoder().encode(env.getProperty("admin.password"))).roles("ADMIN")
                .and().withUser("Nikita").password(passwordEncoder().encode("Fesuso78")).roles("USER");
    }
}






