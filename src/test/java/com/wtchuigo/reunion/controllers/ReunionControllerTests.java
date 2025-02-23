package com.wtchuigo.reunion.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.wtchuigo.reunion.BaseTest;
import com.wtchuigo.reunion.repositories.UserRepository;

@SpringBootTest
public class ReunionControllerTests extends BaseTest {
	
	@MockBean
	UserRepository userRepository;
	
	@Autowired
	ReunionController reunionController;
	
	@Test
	void testRegisterUser() {
		
		
	}

}
