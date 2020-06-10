package com.lhr13.newyorkcab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ters;

@SpringBootApplication
public class NewyorkcabApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewyorkcabApplication.class, args);
    }

}
