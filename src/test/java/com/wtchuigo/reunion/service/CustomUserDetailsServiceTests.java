package com.wtchuigo.reunion.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.wtchuigo.reunion.BaseTest;
import com.wtchuigo.reunion.model.User;
import com.wtchuigo.reunion.repositories.UserRepository;
import com.wtchuigo.reunion.services.CustomUserDetailsService;

@SpringBootTest
public class CustomUserDetailsServiceTests extends BaseTest {
	
	@MockBean
	UserRepository userRepository;
	
	@Autowired
	CustomUserDetailsService userService;
	
	@Test
	void testLoadUserByUsernameThrowsUsernameNotFoundException() {
		when(userRepository.findByUsername(anyString())).thenReturn(null);
		Assertions.assertThrowsExactly(UsernameNotFoundException.class, () -> userService.loadUserByUsername(anyString()));
		verify(userRepository, times(1)).findByUsername(anyString());
	}
	
	@Test
	void testLoadUserByUsername() {
		User user = Instancio.of(User.class).create();
		when(userRepository.findByUsername(anyString())).thenReturn(user);
		Assertions.assertDoesNotThrow(() -> userService.loadUserByUsername(anyString()));
	}

}
