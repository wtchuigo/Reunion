//package com.wtchuigo.reunion.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
///**
// * The persistent class for the users database table.
// * 
// */
////@Entity
//@Data
//@Builder
////@Table(name = "users")
//@NoArgsConstructor
//@AllArgsConstructor
//public class User {
//
////	@Id
////	@GeneratedValue(strategy = GenerationType.IDENTITY)
////	@Column(unique = true, nullable = false)
//	private Long id;
//	private String username;
//	@Column(nullable = false)
//	private String password;
//	private String email;
//	@Enumerated(EnumType.STRING)
//	private Role role;
//
//}