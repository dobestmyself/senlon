package com.ss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SenlonApplication {
    public static void main(String[] args){

        SpringApplication.run(SenlonApplication.class,args);
    }
}
