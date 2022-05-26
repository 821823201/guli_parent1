package com.atgugui.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.atgugui"})
@EnableSwagger2
@EnableWebMvc
public class EduApplication {
    public static void main(String[] args) {

        SpringApplication.run(EduApplication.class, args);
    }
}
