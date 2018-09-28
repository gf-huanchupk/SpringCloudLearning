package com.gf;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ServiceMiyaApplication {

	private static final Logger logger = LoggerFactory.getLogger( ServiceMiyaApplication.class );

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ServiceMiyaApplication.class, args);
	}

	@GetMapping("/hi")
	public String hi() {
		logger.info( "hi is being called" );
		return "hi i'm miya";
	}

	@GetMapping("/miya")
	public String info() {
		logger.info( "info is being called" );
		return restTemplate.getForObject( "http://SERVICE-HI/info" , String.class );
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

}
