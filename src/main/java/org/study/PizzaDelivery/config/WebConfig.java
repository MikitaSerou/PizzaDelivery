package org.study.PizzaDelivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.study.PizzaDelivery.controller"})
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    @Autowired
    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setCharacterEncoding("UTF-8"); //
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);

        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setCache(false);
        registry.viewResolver(resolver);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/")
                .setCachePeriod(31556926);  // "/resources/"
    }


    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/message");
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setCacheSeconds(0);
        messageSource.setDefaultEncoding("UTF-8"); //СУКА ШО НЕ ТАК НАХУЙ!!!

        return messageSource;
    }


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("ru"));  //new Locale("ru")  Locale.ENGLISH

        return resolver;
    }

    @Bean
    public UrlTemplateResolver urlTemplateResolver() {
        return new UrlTemplateResolver();
    }
///

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}


