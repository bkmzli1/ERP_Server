package ru.bkmz.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ru.bkmz.demo.util.FileCreate;

import java.util.Locale;

@Configuration
@CrossOrigin(origins = "http://localhost:4200")
public class InternationalizationConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
        new FileCreate(uploadPath);
        new FileCreate(uploadPath+"/privet");
        new FileCreate(uploadPath+"/public");

        registry.addResourceHandler("/public/**")
                .addResourceLocations("file:///" + uploadPath + "/public/");
        System.out.println("file:///" + uploadPath + "/public/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}

