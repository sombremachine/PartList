package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


// т.к. мы собираем war который будет разворачиваться на tomcat то надо наследоваться от SpringBootServletInitializer
// если бы делали jar то наследоваться не надо было бы.

// @SpringBootApplication включает в себя:
//
//@Configuration - to enable Java-based configuration
//@EnableAutoConfiguration - to enable Spring Boot's auto-configuration feature
//@EnableWebMvc
//@ComponentScan - to enable component scanning

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        // стандартная строчка запуска приложения. SpringApplication.run() запускает фреймворк
        SpringApplication.run(Application.class, args);
    }
}
