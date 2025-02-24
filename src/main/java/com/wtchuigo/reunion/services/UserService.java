package com.wtchuigo.reunion.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wtchuigo.reunion.exceptions.ReunionException;
import com.wtchuigo.reunion.model.User;
import com.wtchuigo.reunion.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public void saveUser(User user) {
		if (user.getUsername() == null) {
		    throw new IllegalArgumentException("Username cannot be null");
		  }
		if (userRepository.findByUsername(user.getUsername()) != null) {
			throw new ReunionException("Username already exists");
		}
		if (user.getPassword() == null) {
		    throw new IllegalArgumentException("Password cannot be null");
		  }
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
