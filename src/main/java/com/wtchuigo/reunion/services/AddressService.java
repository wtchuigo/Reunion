package com.wtchuigo.reunion.services;

import java.util.List;

import com.wtchuigo.reunion.model.Address;

public interface AddressService {
	
	void save(Address address);
	void delete(Address address);
	List<Address> findAll();

}
