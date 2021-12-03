package com.olx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginDelegateImpl implements LoginDelegate {

	@Autowired
	RestTemplate restTemplate;

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplates() {
		return new RestTemplate();
	}

	@Override
	@CircuitBreaker(name = "VALIDTOKEN_CIRCUTE_BREAKER", fallbackMethod = "fallBackGetValidToken")
	public boolean isTokenvalidate(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", token);
		HttpEntity entity = new HttpEntity(headers);

		ResponseEntity<Boolean> result = this.restTemplate.exchange("http://API-GATEWAY/olx/user/validate/token",
				HttpMethod.GET, entity, Boolean.class);

		// to call login microservice call using Rest Template login-service
		// Boolean value =
		// this.restTemplate.getForObject("http://localhost:9002/olx/user/validate/token",Boolean.class);
		// Boolean value =
		// this.restTemplate.getForObject("http://API-GATEWAY/olx/user/validate/token",Boolean.class);
		return result.getBody();
	}

	public boolean fallBackGetValidToken(String token, Throwable ex) {
		System.out.println("Login Service valid token failed" + ex.getMessage());
		return false;
	}

}
