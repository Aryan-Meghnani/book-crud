package com.example.bookcrud;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@JsonIgnoreProperties(value ={"handler","hibernateLazyInitializer","FileHandler"})
public class BookCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookCrudApplication.class, args);

    }

}
