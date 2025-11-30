package com.fhc.leadmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhc.leadmanagement.dto.SubscriptionDto;
import com.fhc.leadmanagement.entity.Subscriptions;
import com.fhc.leadmanagement.repository.SubscriptionsRepository;
import com.fhc.leadmanagement.service.SubscriptionsService;

@RestController
@RequestMapping("/api/subs")
@CrossOrigin(origins = "http://localhost:9091") // Adjust per your frontend origin
public class SubscriptionsController {

	@Autowired
	private SubscriptionsService subscriptionService;

	@Autowired
	private SubscriptionsRepository subsRepository;

	@PostMapping
	public ResponseEntity<Subscriptions> saveSubscription(@RequestBody SubscriptionDto subscriptionDto) {
		// Map DTO to entity
		Subscriptions subscriptionEntity = mapDtoToEntity(subscriptionDto);
		// Call service with userId and entity
		Subscriptions savedSubscription = subscriptionService.saveSubscription(subscriptionDto.getUserId(),
				subscriptionEntity);
		return ResponseEntity.ok(savedSubscription);
	}

	private Subscriptions mapDtoToEntity(SubscriptionDto dto) {
		Subscriptions entity = new Subscriptions();
		entity.setEndpoint(dto.getEndpoint());
		entity.setP256dh(dto.getP256dh());
		entity.setAuth(dto.getAuth());
		entity.setDeviceType(dto.getDeviceType());
		// Users will be set in service layer based on userId
		return entity;
	}

	@GetMapping("/all")
	public List<Subscriptions> getAllSubs() {
		return subsRepository.findAll();
	}

	@DeleteMapping("/userid/{userId}")
	public ResponseEntity<Void> deleteByUserId(@PathVariable Long userId) {
		subscriptionService.removeUserMappings(userId);
		return ResponseEntity.noContent().build();
	}
}
