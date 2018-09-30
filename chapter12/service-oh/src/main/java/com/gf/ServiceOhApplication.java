package com.gf;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
public class ServiceOhApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOhApplication.class, args);
	}

	@Value( "${server.port}" )
	private String port;

	@GetMapping("/oh")
	@HystrixCommand(fallbackMethod = "ohError")
	public String oh() {
		return "oh , port is " + port;
	}

	public String ohError() {
		return "oh , server error";
	}

}
