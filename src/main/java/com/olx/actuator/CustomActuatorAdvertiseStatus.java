package com.olx.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.olx.service.AdvertiseDataService;

@Component
public class CustomActuatorAdvertiseStatus implements InfoContributor {
	@Autowired
	AdvertiseDataService advertiseDataService;

	@Override
	public void contribute(Builder builder) {
		// TODO Auto-generated method stub
		Map<String, Integer> userDetails = new HashMap<>();
		userDetails.put("No of Advertises", advertiseDataService.getAllAdvertises().size());
		userDetails.put("No of Active Advertises ", 50);
		userDetails.put("No of Closed Advertises", 50);
		userDetails.put("No of Open Advertises", 50);
		userDetails.put(" No of Advertises by username", 50);

		builder.withDetail("users", userDetails);

	}

}