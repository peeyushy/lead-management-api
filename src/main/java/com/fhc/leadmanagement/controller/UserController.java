package com.fhc.leadmanagement.controller;

import com.fhc.leadmanagement.entity.User;
import com.fhc.leadmanagement.service.UserService;
import com.fhc.leadmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow();
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		User existingUser = userRepository.findById(id).orElseThrow();
		updateUserDetails(existingUser, userDetails);
		return userRepository.save(existingUser);
	}

	// method to fetch user by username
	@GetMapping("/username/{username}")
	public User getUserByUserName(@PathVariable String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
	}

	private void updateUserDetails(User existingUser, User userDetails) {
		existingUser.setFullname(userDetails.getFullname());
		existingUser.setEmail(userDetails.getEmail());
		existingUser.setRole(userDetails.getRole());
		existingUser.setTeamleaderid(userDetails.getTeamleaderid());
		existingUser.setUpdatedat(LocalDateTime.now());
		if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
			existingUser.setPassword(userDetails.getPassword());
		}
	}
}