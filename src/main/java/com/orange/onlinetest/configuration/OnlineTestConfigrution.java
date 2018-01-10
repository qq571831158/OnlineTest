package com.orange.onlinetest.configuration;

import com.orange.onlinetest.interceptor.LoginIntecepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Component
public class OnlineTestConfigrution extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginIntecepter loginIntecepter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntecepter);
        super.addInterceptors(registry);
    }
}
