package com.iiht.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.annotation.Order;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
//@EnableSwagger2
public class StockMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMarketApplication.class, args);
	}

}
