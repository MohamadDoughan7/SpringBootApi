package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class of the spring boot application.
 */
@SpringBootApplication
@MapperScan("com.example.demo.student")
public class DemoApplication {

  /**
   * This is the main method that runs the application.
   */
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);

  }


}
