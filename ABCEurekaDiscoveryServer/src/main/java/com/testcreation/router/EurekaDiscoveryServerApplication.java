package com.testcreation.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.testcreation.router.exception.RestTemplateResponseErrorHandler;

@EnableEurekaServer
@SpringBootApplication
public class EurekaDiscoveryServerApplication {

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryServerApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return restTemplateBuilder
		          .errorHandler(new RestTemplateResponseErrorHandler())
		          .build();
	}
	
	@Bean
	public HttpHeaders getHttpHeaders() {
		return new HttpHeaders();
	}
	
}



