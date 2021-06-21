package com.company.projectserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ProjectServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceRegistryApplication.class, args);
	}

}
