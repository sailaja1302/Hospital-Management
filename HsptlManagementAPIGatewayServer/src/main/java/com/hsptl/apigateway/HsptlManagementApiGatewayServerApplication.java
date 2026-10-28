package com.hsptl.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HsptlManagementApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsptlManagementApiGatewayServerApplication.class, args);
	}

}
