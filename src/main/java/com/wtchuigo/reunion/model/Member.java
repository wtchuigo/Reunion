package com.wtchuigo.reunion.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the members database table.
 * 
 */
@Entity
@Data
@Builder
@Table(name="members")
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Date birthdate;

	@Column(name="create_date")
	private Date createDate;

	@NotBlank(message = "Email is mandatory")
	private String email;

	@Column(name="first_name", length=45)
	@NotBlank(message = "First Name cannot be null")
	private String firstName;

	@Column(name="last_name", length=45)
	@NotBlank(message = "Last name is mandatory")
	private String lastName;

	@Column(name="last_update")
	private Date lastUpdate;

	private String phone;

	private String role;
	
	@Column(name="hosting_date")
	private Date hostingDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_fk", referencedColumnName="id")
	private Address address;

}