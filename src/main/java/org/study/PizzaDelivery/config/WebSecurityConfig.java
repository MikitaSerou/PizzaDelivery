package org.study.PizzaDelivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.study.PizzaDelivery.data.service.UserService;


@Configuration
@EnableWebSecurity
@PropertySource({"classpath:db.properties"})
//@ComponentScan(basePackages = {"org.study.PizzaDelivery.data.service"})
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
                .antMatchers("/news").hasRole("USER")
                //Access for all:
                .antMatchers("/", "/resources/**", "/category", "/category/{categoryName}" ).permitAll()
                //All other pages require authentication:
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                //Redirect to main page after successful sign in:
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().deleteCookies("JSESSIONID")
                .permitAll()
                .logoutSuccessUrl("/")
                .and()
                .rememberMe().key("uniqueAndSecret");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        //Default admin/user in memory
        auth.inMemoryAuthentication().withUser(env.getProperty("admin.login"))
                .password(passwordEncoder().encode(env.getProperty("admin.password"))).roles("ADMIN", "USER")
                .and().withUser("Nikita").password(passwordEncoder().encode("Fesuso78")).roles("USER");
    }
}






