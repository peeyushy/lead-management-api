package com.fhc.leadmanagement.service;

import java.time.LocalDateTime;

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

    // Additional user management operations as needed
}