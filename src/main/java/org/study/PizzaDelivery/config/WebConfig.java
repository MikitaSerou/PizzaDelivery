package org.study.PizzaDelivery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;


import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.study.PizzaDelivery.controller"})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    public WebConfig(ApplicationContext applicationContext) {
    }


    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {

        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(31556926);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource =
                new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/message");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());

        return validator;
    }

    @Bean
    public ThemeSource themeSource() {
        ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
        themeSource.setBasenamePrefix("theme/");

        return themeSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("ru"));

        return resolver;
    }

    @Bean
    public ThemeResolver themeResolver() {
        SessionThemeResolver resolver = new SessionThemeResolver();
        resolver.setDefaultThemeName("dark");

        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
        themeChangeInterceptor.setParamName("theme");
        registry.addInterceptor(themeChangeInterceptor);

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 10MB
        multipartResolver.setMaxUploadSizePerFile(10485760); // 10MB
        return multipartResolver;
    }
}
