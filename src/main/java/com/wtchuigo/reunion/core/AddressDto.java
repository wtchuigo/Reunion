package com.wtchuigo.reunion.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

	private int id;

	private String city;

	private String country;

	private String postalCode;

	private String street;

}