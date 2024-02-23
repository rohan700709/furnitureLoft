package com.niit.furnitureCartService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FurnitureCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurnitureCartServiceApplication.class, args);

	}

}
