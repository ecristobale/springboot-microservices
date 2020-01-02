package com.ecristobale.springboot.app.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringbootServiceSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiceSchoolApplication.class, args);
	}

}
