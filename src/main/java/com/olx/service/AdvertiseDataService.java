package com.olx.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.olx.dto.Advertisements;
import com.olx.entity.AdvertisementEntity;

public interface AdvertiseDataService {

	public Advertisements createNewAdvertise(Advertisements advertisements, String authToken);

	public Advertisements updateAdvertiseId(int advertiseId, Advertisements newAdvertisement, String authToken);

	public List<Advertisements> getAllUserAdvertise(String authToken);

	public Advertisements getUserAdvertiseId(int advertiseId, String authToken);

	public boolean deleteUserAdvertiseById(int advertiseId, String authToken);

	public List<Advertisements> searchAdvertisesByFilterCriteria(String searchText, int categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortType,
			int startIndex, int records);

	public List<Advertisements> searchAdvertisesBySearchText(String keyword);

	public Advertisements getadvertiseById(int advertiseId, String authToken);

	public List<Advertisements> getAllAdvertises();

}
