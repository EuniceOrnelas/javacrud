// Importaciones de clases de Spring Boot necesarias
package com.usersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Anotación que combina @Configuration, @EnableAutoConfiguration y @ComponentScan
public class UsersApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApiApplication.class, args);
    }
}
