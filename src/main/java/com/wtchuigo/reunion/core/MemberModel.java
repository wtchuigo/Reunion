package com.wtchuigo.reunion.core;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class MemberModel {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Past(message = "The date of birth must be in the past.")
	private Date birthdate;

	@NotBlank(message = "Email is mandatory")
	private String email;

	@NotBlank(message = "First name is mandatory")
	private String firstName;

	@NotBlank(message = "Last name is mandatory")
	private String lastName;

	private String phone;

	private String role;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date hostingDate;

	private String city;

	private String country;

	private String postalCode;

	private String street;

}