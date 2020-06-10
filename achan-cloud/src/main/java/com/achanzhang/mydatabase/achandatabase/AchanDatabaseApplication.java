package com.achanzhang.mydatabase.achandatabase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

@SpringBootApplication
//@MapperScan(basePackages = "com.achanzhang.mydatabase.achandatabase.dao")
//@ComponentScan(basePackages = {"com.achanzhang.mydatabase.achandatabase.*", "com.achanzhang.mydatabase.achandatabase.controller"})
public class AchanDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AchanDatabaseApplication.class, args);
    }

}
