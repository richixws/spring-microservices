package com.example.springboot.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringBootServiciosEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootServiciosEurekaServerApplication.class, args);
	}

}
