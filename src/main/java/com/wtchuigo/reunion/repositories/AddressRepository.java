package com.wtchuigo.reunion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wtchuigo.reunion.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
