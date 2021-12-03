package com.olx.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.olx.dto.Advertisements;
import com.olx.entity.AdvertisementEntity;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.InvalidUserException;
import com.olx.repo.AdvertisementRepo;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.slf4j.LoggerFactory;

@Service
public class AdvertiseDataServiceImpl implements AdvertiseDataService {
	Logger logger = LoggerFactory.getLogger(AdvertiseDataServiceImpl.class);
	@Autowired
	MasterDataDelegate masterDataDelegate;
	@Autowired
	private AdvertisementRepo advertisementRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	LoginDelegate loginDataDelegate;
	@Autowired
	EntityManager entityManager;

	private AdvertisementEntity getAdvertisementsEntityFromDTO(Advertisements advertisements) {
		AdvertisementEntity advertisementEntity = this.modelMapper.map(advertisements, AdvertisementEntity.class);
		return advertisementEntity;
	}

	private Advertisements getAdvertisementsDTOFromEntity(AdvertisementEntity advertisementEntity) {
		Advertisements advertisementsDTO = this.modelMapper.map(advertisementEntity, Advertisements.class);
		return advertisementsDTO;
	}

	private List<Advertisements> getAdvertisementsDTOListFromEntityList(
			List<AdvertisementEntity> advertisementsEntityList) {
		List<Advertisements> advertisementsDTOList = new ArrayList<Advertisements>();
		for (AdvertisementEntity stockEntity : advertisementsEntityList) {
			advertisementsDTOList.add(getAdvertisementsDTOFromEntity(stockEntity));
		}
		return advertisementsDTOList;
	}

	@ExceptionHandler(value = { InvalidAdvertiseIdException.class })
	public ResponseEntity<String> handleInvalidStockIdError(RuntimeException exception, WebRequest request) {

		return new ResponseEntity<String>("Local Handler Invalid InvalidAdvertise Id ", HttpStatus.BAD_REQUEST);
	}

	@Override
	public Advertisements createNewAdvertise(Advertisements advertisements, String authToken) {

		if (!loginDataDelegate.isTokenvalidate(authToken)) {
			throw new InvalidUserException(authToken);
		}

		AdvertisementEntity stockEntity = getAdvertisementsEntityFromDTO(advertisements);
		stockEntity = advertisementRepo.save(stockEntity);
		return getAdvertisementsDTOFromEntity(stockEntity);
	}

	@Override
	public Advertisements updateAdvertiseId(int advertiseId, Advertisements newAdvertisement, String authToken) {
		if (!loginDataDelegate.isTokenvalidate(authToken)) {
			throw new InvalidUserException(authToken);
		}

		Optional<AdvertisementEntity> opstockentity = advertisementRepo.findById(advertiseId);
		if (opstockentity.isPresent()) {
			AdvertisementEntity advertisementEntity = opstockentity.get();
			advertisementEntity.setTitle(newAdvertisement.getTitle());
			advertisementEntity.setPrice(newAdvertisement.getPrice());
			advertisementEntity.setCategoryId(newAdvertisement.getCategoryId());
			advertisementEntity.setDescription(newAdvertisement.getDescription());
			advertisementEntity.setStatus(newAdvertisement.getStatus());
			advertisementEntity.setCreated_date(newAdvertisement.getCreated_date());
			advertisementEntity.setModified_date(newAdvertisement.getModified_date());
			advertisementEntity.setActive(newAdvertisement.getActive());
			advertisementEntity.setUsername(newAdvertisement.getUsername());
			advertisementRepo.save(advertisementEntity);
		}
		return null;
	}

	@Override
	public List<Advertisements> getAllUserAdvertise(String authToken) {
		if (!loginDataDelegate.isTokenvalidate(authToken)) {
			throw new InvalidUserException(authToken);
		}
		List<AdvertisementEntity> stockEntityList = advertisementRepo.findAll();
		return getAdvertisementsDTOListFromEntityList(stockEntityList);
	}

	@Override
	public Advertisements getUserAdvertiseId(int advertiseId, String authToken) {
		if (!loginDataDelegate.isTokenvalidate(authToken)) {
			throw new InvalidUserException(authToken);
		}
		Optional<AdvertisementEntity> opstockentity = advertisementRepo.findById(advertiseId);
		if (opstockentity.isPresent()) {
			AdvertisementEntity advertisementEntity = opstockentity.get();
			return getAdvertisementsDTOFromEntity(advertisementEntity);
		}
		return null;
	}

	@Override
	public boolean deleteUserAdvertiseById(int advertiseId, String authToken) {
		if (!loginDataDelegate.isTokenvalidate(authToken)) {
			throw new InvalidUserException(authToken);
		}
		advertisementRepo.deleteById(advertiseId);
		return true;
	}

	@Override
	public List<Advertisements> searchAdvertisesByFilterCriteria(String searchText, int categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortType,
			int pageNumber, int pageSize) {
		Sort sort = null;
		if ("ASC".equalsIgnoreCase(sortType)) {
			sort = Sort.by("id").ascending();
		}
		if ("DESC".equalsIgnoreCase(sortType)) {
			sort = Sort.by("id").descending();
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<AdvertisementEntity> result = advertisementRepo.findAll((root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (searchText != null) {
				predicates.add(builder.equal(root.get("title"), searchText));
				predicates.add(builder.equal(root.get("categoryId"), categoryId));
				predicates.add(builder.equal(root.get("username"), postedBy));
				predicates.add(builder.equal(root.get("created_date"), onDate));
				predicates.add(builder.equal(root.get("modified_date"), toDate));
			}

			return builder.and(predicates.toArray(new Predicate[] {}));

		}, pageable);

		List<AdvertisementEntity> EntityList = result.getContent();

		return getAdvertisementsDTOListFromEntityList(EntityList);
	}

	@Override
	public List<Advertisements> searchAdvertisesBySearchText(String keyword) {
		List<AdvertisementEntity> advertisementEntityList = advertisementRepo.findAllBySearch(keyword);
		return getAdvertisementsDTOListFromEntityList(advertisementEntityList);
	}

	@Override
	public Advertisements getadvertiseById(int advertiseId, String authToken) {

		if (!loginDataDelegate.isTokenvalidate(authToken)) {
			throw new InvalidUserException(authToken);
		}
		System.out.println("print value : " + loginDataDelegate.isTokenvalidate(authToken));

		Optional<AdvertisementEntity> opstockentity = advertisementRepo.findById(advertiseId);
		if (opstockentity.isPresent()) {
			AdvertisementEntity advertisementEntity = opstockentity.get();
			return getAdvertisementsDTOFromEntity(advertisementEntity);
		}
		return null;
	}

	@Override
	public List<Advertisements> getAllAdvertises() {
		List<AdvertisementEntity> advertisementEntityList = advertisementRepo.findAll();
		return getAdvertisementsDTOListFromEntityList(advertisementEntityList);
	}

}
