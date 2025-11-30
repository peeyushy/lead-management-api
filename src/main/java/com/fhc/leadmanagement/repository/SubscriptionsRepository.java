package com.fhc.leadmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhc.leadmanagement.entity.Subscriptions;

import jakarta.transaction.Transactional;

public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Long> {

	List<Subscriptions> findAll();

	Optional<Subscriptions> findByEndpoint(String endpoint);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM user_subscription_map WHERE user_id = :userId", nativeQuery = true)
	void deleteMappingsByUserId(@Param("userId") Long userId);
}
