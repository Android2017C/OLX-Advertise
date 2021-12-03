package com.olx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MasterDataDelegatelmpl implements MasterDataDelegate {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {

		return new RestTemplate();
	}

	@Override
	@CircuitBreaker(name = "CATEGORY_CIRCUTE_BREAKER", fallbackMethod = "fallBackGetAllCategories")
	public List<Map> getAllCategories() {
		// to call masterdata microservice call using Rest Template
		// List list =
		// this.restTemplate.getForObject("http://localhost:9001/olx/advertise/category",List.class);
		// List list =
		// this.restTemplate.getForObject("http://MasterData-service/olx/advertise/category",List.class);
		List list = this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/category", List.class);
		return list;
	}

	@Override
	@CircuitBreaker(name = "STATUS_CIRCUTE_BREAKER", fallbackMethod = "fallBackGetAllStatus")
	public List<Map> getAllStatus() {
		// to call masterdata microservice call using Rest Template
		// List list =
		// this.restTemplate.getForObject("http://localhost:9001/olx/advertise/status",List.class);
		// List list =
		// this.restTemplate.getForObject("http://MasterData-service/olx/advertise/status",List.class);
		List list = this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/status", List.class);
		return list;
	}

	public List<Map> fallBackGetAllCategories(Throwable ex) {
		System.out.println("Masterdata category failed" + ex.getMessage());
		return null;
	}

	public List<Map> fallBackGetAllStatus(Throwable ex) {
		System.out.println("Masterdata Status failed" + ex.getMessage());
		return null;
	}

}
