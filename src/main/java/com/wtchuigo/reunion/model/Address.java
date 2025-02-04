package com.wtchuigo.reunion.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the addresses database table.
 * 
 */
@Entity
@Data
@Builder
@Table(name="addresses")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private String city;

	private String country;
	
	@Column(name="last_update")
	private Date lastUpdate;

	@Column(name="postal_code")
	private String postalCode;

	private String street;

	@OneToOne(mappedBy="address")
	private Member member;

}