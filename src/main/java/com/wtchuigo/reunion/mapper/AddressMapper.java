package com.wtchuigo.reunion.mapper;

import org.mapstruct.Mapper;

import com.wtchuigo.reunion.core.AddressDto;
import com.wtchuigo.reunion.model.Address;

@Mapper
public interface AddressMapper {
	
//	AddressDto addressToAddressDto(Address address);
	Address addressDtoToAddress(AddressDto addressDto);

}
