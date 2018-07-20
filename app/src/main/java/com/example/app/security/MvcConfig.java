package com.example.app.security;

import com.example.app.controller.Routes;
import com.example.app.controller.UI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(Routes.ROOT).setViewName(UI.LOGIN);
        registry.addViewController(Routes.LOGIN).setViewName(UI.LOGIN);
        registry.addViewController(Routes.BUSINESS).setViewName(UI.BUSINESS_TRIPS);
        registry.addViewController(Routes.MANAGEMENT).setViewName(UI.MANAGEMENT);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

