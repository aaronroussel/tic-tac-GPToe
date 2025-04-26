package com.aaron.APIServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aaron.APIServer", "com.aaron.APIServer.controllers"})
public class ApiServerApplication {

  public static void main(String[] args) {
      SpringApplication.run(ApiServerApplication.class, args);
  }
}
