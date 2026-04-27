package com.mns.cda.myrecipe_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyRecipeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRecipeApiApplication.class, args);
    }

}
