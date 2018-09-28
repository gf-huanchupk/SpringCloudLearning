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

@EnableEurekaClient
@SpringBootApplication
@RestController
public class ServiceHiApplication {

	private static final Logger logger = LoggerFactory.getLogger( ServiceHiApplication.class );

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ServiceHiApplication.class, args);
	}

	@GetMapping("/hi")
	public String hi() {
		logger.info( "calling trace service-hi" );
		return restTemplate.getForObject( "http://SERVICE-MIYA/miya" , String.class );
	}

	@GetMapping("/info")
	public String info() {
		logger.info( "calling trace service-hi " );
		return "i'm service-hi";
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
