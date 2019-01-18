package com.fuhui;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
//@EnableCasClient // 开启CAS支持
public class FuhuiApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FuhuiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FuhuiApplication.class, args);
    }
}
