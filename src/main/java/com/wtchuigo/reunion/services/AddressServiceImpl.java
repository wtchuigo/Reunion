package com.wtchuigo.reunion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtchuigo.reunion.model.Address;
import com.wtchuigo.reunion.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void save(Address address) {
		addressRepository.save(address);		
	}

	@Override
	public void delete(Address address) {
		addressRepository.delete(address);		
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

}
