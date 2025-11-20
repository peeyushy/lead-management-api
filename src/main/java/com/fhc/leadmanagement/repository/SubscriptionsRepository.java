package com.fhc.leadmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhc.leadmanagement.entity.Subscriptions;

public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Long> {

	Optional<Subscriptions> findByEndpoint(String endpoint);
}
