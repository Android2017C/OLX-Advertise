package com.olx;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
@EnableEurekaClient
public class OlxAdvertiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxAdvertiseApplication.class, args);
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("OLX Advertisement REST API Documentation",
				"OLX Advertisement REST APIs released by Zensar Ltd.", "2.5", "http://zensar.com/termsofservice",
				new Contact("Srini", "http://srini.com", "sreenivasulu.c@zensar.com"), "GPL", "http://gpl.com",
				new ArrayList<VendorExtension>());
	}

	@Bean
	public ModelMapper getModelMapper() {

		return new ModelMapper();
	}

	@Bean
	public Docket getCustomizedDocket() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.olx"))
				// .paths(PathSelectors.ant("/advertise/**"))
				.paths(PathSelectors.any()).build();
	}
}
