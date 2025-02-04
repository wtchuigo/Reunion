package com.wtchuigo.reunion.core;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

	
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
	
	private Long id;

	private Date birthdate;

	private String email;

	private String firstName;

	private String lastName;

	private String phone;

	private String role;
	
	private Date hostingDate;

	private AddressDto address;

}