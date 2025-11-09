package com.fhc.leadmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhc.leadmanagement.entity.User;
import com.fhc.leadmanagement.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		user.setCreatedat(LocalDateTime.now());
		user.setUpdatedat(LocalDateTime.now());
		return userRepository.save(user);
	}

	// Get user by ID, throws if not found
	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	}

	// Get list of executive user IDs under a Team Leader
	public List<Long> getExecutivesByTeamLeader(Long teamLeaderId) {
		List<User> executives = userRepository.findExecutivesByTeamLeaderId(teamLeaderId);
		return executives.stream().map(User::getId).collect(Collectors.toList());
	}
}