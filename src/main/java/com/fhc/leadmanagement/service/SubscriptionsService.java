package com.fhc.leadmanagement.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fhc.leadmanagement.entity.Subscriptions;
import com.fhc.leadmanagement.entity.User;
import com.fhc.leadmanagement.repository.SubscriptionsRepository;
import com.fhc.leadmanagement.repository.UserRepository;

@Service
public class SubscriptionsService {

	@Autowired
	private SubscriptionsRepository subscriptionRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void addOrUpdateUserSubscription(Long userId, Subscriptions subscriptionDto) {
		Optional<Subscriptions> existingSubOpt = subscriptionRepository.findByEndpoint(subscriptionDto.getEndpoint());
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

		Subscriptions subscription;
		if (existingSubOpt.isPresent()) {
			// Subscription exists: update users set to include current user
			subscription = existingSubOpt.get();
			if (subscription.getUsers() == null) {
				subscription.setUsers(new HashSet<>());
			}
			subscription.getUsers().add(user);
		} else {
			// New subscription, add user to users set
			subscription = subscriptionDto;
			Set<User> users = new HashSet<>();
			users.add(user);
			subscription.setUsers(users);
		}

		subscriptionRepository.save(subscription);
	}

	public Set<Subscriptions> getUserSubscriptions(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

		return user.getSubscriptions();
	}

	@Transactional
	public Subscriptions saveSubscription(Long userId, Subscriptions subscription) {
		if (userId == null) {
			throw new IllegalArgumentException("User ID cannot be null");
		}

		// Fetch the User entity by id
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found for id " + userId));

		// Initialize users set if null
		if (subscription.getUsers() == null) {
			subscription.setUsers(new HashSet<>());
		}

		// Add user to subscription's users set
		subscription.getUsers().add(user);

		// Also add subscription to user's subscriptions set (to keep both sides in
		// sync)
		if (user.getSubscriptions() == null) {
			user.setSubscriptions(new HashSet<>());
		}
		user.getSubscriptions().add(subscription);

		// Save user if owning side; assuming User owns the relation with proper cascade
		// set
		userRepository.save(user);

		// Return the saved subscription entity (it should have generated ID and
		// relations set)
		return subscription;
	}

	@Transactional(readOnly = true)
	public List<Subscriptions> getAllSubscriptions() {
		return subscriptionRepository.findAll();
	}

	@Transactional
    public void removeUserMappings(Long userId) {
		subscriptionRepository.deleteMappingsByUserId(userId);
    }
}
