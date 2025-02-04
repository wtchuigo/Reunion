package com.wtchuigo.reunion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtchuigo.reunion.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
