package com.nttdata.credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ApiBankCreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBankCreditApplication.class, args);
	}

}
