package com.olx.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.olx.entity.AdvertisementEntity;

public interface AdvertisementRepo
		extends JpaRepository<AdvertisementEntity, Integer>, JpaSpecificationExecutor<AdvertisementEntity> {

	@Query("SELECT a FROM AdvertisementEntity a WHERE "
			+ "CONCAT(a.id, a.title, a.price, a.categoryId, a.description, a.status, a.created_date, a.modified_date, a.active, a.username)"
			+ " LIKE %?1%")
	List<AdvertisementEntity> findAllBySearch(String keyword);

}
