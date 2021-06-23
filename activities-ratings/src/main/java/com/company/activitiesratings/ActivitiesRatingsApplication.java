package com.company.activitiesratings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ActivitiesRatingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiesRatingsApplication.class, args);
	}

}
