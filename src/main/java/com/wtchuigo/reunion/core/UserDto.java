package com.wtchuigo.reunion.core;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String username;
	private String password;
	private String role;

}