package com.testcreation.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.testcreation.trainer.exception.RestTemplateResponseErrorHandler;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
public class TrainerApplication {

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	public static void main(String[] args) {
		SpringApplication.run(TrainerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return restTemplateBuilder
		          .errorHandler(new RestTemplateResponseErrorHandler())
		          .build();
	}
	
}
