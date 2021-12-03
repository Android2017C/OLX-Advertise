package com.olx.controller;

import java.time.LocalDate;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Advertisements;
import com.olx.entity.AdvertisementEntity;
import com.olx.service.AdvertiseDataService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/advertise")
public class AdvertiseController {

	@Autowired
	AdvertiseDataService advertiseDataService;

//7 post request
	@PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Create NewAdvertise by passing Auth token")
	public Advertisements createNewAdvertise(@RequestBody Advertisements advertisements,
			@RequestHeader("auth-token") String authToken) {

		return advertiseDataService.createNewAdvertise(advertisements, authToken);
	}

//Update request
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Update exist Advertise by id along with passing Auth token")
	public Advertisements updateAdvertiseId(@PathVariable("id") int advertiseId,
			@RequestBody Advertisements newAdvertisement, @RequestHeader("auth-token") String authToken) {

		return advertiseDataService.updateAdvertiseId(advertiseId, newAdvertisement, authToken);
	}

//9 get user advertise
	@GetMapping(value = "/user/advertise", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Get All User Advertises by passing Auth token")
	public Collection<Advertisements> getAllUserAdvertise(@RequestHeader("auth-token") String authToken) {
		return advertiseDataService.getAllUserAdvertise(authToken);
	}

//10 get user advertise by id 
	@GetMapping(value = "/user/advertise/{advertiseId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Get User Advertise by id along with passing Auth token")
	public Advertisements getUserAdvertiseId(@PathVariable("advertiseId") int advertiseId,
			@RequestHeader("auth-token") String authToken) {
		return advertiseDataService.getUserAdvertiseId(advertiseId, authToken);
	}

// 11 delete  user advertise by id 
	@DeleteMapping(value = "/user/advertise/{advertiseId}")
	@ApiOperation(value = "Delete User Advertise by id along with passing Auth token")
	public boolean deleteUserAdvertiseById(@PathVariable("id") int advertiseId,
			@RequestHeader("auth-token") String authToken) {

		return advertiseDataService.deleteUserAdvertiseById(advertiseId, authToken);
	}

//12 Search Advertise By filter
	@GetMapping(value = "/search/filtercriteria", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Search Filter Advertisement by searchText and category and postedBy  and dateCondition and onDate , fromDate,toDate,sortType,\r\n"
			+ "			startIndex,records")
	public List<Advertisements> searchAdvertisesByFilterCriteria(@RequestParam("searchText") String searchText,
			@RequestParam(name = "categoryId", required = false) int categoryId,
			@RequestParam(name = "postedBy", required = false) String postedBy,
			@RequestParam(name = "dateCondition", required = false) String dateCondition,
			@RequestParam(name = "onDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam(name = "sortType", required = false) String sortType,
			@RequestParam(name = "startIndex", required = false) int startIndex,
			@RequestParam(name = "records", required = false) int records) {

		return advertiseDataService.searchAdvertisesByFilterCriteria(searchText, categoryId, postedBy, dateCondition,
				onDate, fromDate, toDate, sortType, startIndex, records);
	}

//13 Advertise search text
	@GetMapping(value = "/search", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Search Advertise by searchText")
	public List<Advertisements> searchAdvertisesBySearchText(@RequestParam("searchText") String searchText) {

		return advertiseDataService.searchAdvertisesBySearchText(searchText);
	}

//14 delete  user advertise by id 
	@GetMapping(value = "/{advertiseId}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Get Advertise by id along with passing Auth token")
	public Advertisements getAdvertiseById(@PathVariable("advertiseId") int advertiseId,
			@RequestHeader("auth-token") String authToken) {
		return advertiseDataService.getadvertiseById(advertiseId, authToken);
	}

// get request
	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Get All Advertisements")
	public Collection<Advertisements> getAllAdvertises() {
		return advertiseDataService.getAllAdvertises();
	}

}
