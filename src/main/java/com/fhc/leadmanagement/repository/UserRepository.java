package com.fhc.leadmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fhc.leadmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	// Find user by ID (default method)
	Optional<User> findById(Long id);

	// Get list of executives under a given team leader	
	@Query("SELECT u FROM User u WHERE u.teamleaderid = :teamLeaderId AND u.role = 'EXECUTIVE'")
	List<User> findExecutivesByTeamLeaderId(@Param("teamLeaderId") Long teamLeaderId);

}
