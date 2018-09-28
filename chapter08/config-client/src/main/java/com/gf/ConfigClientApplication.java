package com.gf;

		import org.springframework.beans.factory.annotation.Value;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.cloud.context.config.annotation.RefreshScope;
		import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
		import org.springframework.web.bind.annotation.GetMapping;
		import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
@RefreshScope
public class ConfigClientApplication {

	/**
	 *	http://localhost:8881/actuator/bus-refresh
	 */

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	@Value("${foo}")
	String foo;

	@GetMapping("/hi")
	public String hi() {
		return foo;
	}

}
