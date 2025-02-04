package com.wtchuigo.reunion.core;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the users database table.
 * 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;
	private String username;
	private String password;
	private String role;

}