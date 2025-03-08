package com.wtchuigo.reunion.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.wtchuigo.reunion.BaseTest;
import com.wtchuigo.reunion.core.AddressDto;
import com.wtchuigo.reunion.mapper.AddressMapper;
import com.wtchuigo.reunion.model.Address;
import com.wtchuigo.reunion.repositories.AddressRepository;
import com.wtchuigo.reunion.services.AddressService;

@SpringBootTest
class AddressServiceTests extends BaseTest {

	@Autowired
	AddressService addressService;
	@Autowired
	private AddressMapper addressMapper;

	@MockBean
	AddressRepository addressRepository;

	@Test
	void testFindAll() {
		List<Address> addresses = Instancio.ofList(Address.class).create();
		when(addressRepository.findAll()).thenReturn(addresses);
		List<Address> result = addressService.findAll();
		assertThat(result.size()).isEqualTo(addresses.size());
		Assertions.assertTrue(result.containsAll(addresses));
		
	}
	
	@Test
	void testSave() {
		AddressDto address = Instancio.of(AddressDto.class).create();
		when(addressRepository.save(any(Address.class))).thenReturn(addressMapper.addressDtoToAddress(address));
		
		addressService.save(address);
		// Assert: Verify the method was called once
        verify(addressRepository, times(1)).save(any(Address.class));
	}
	
	@Test
	void testDelete() {
		Optional<Address> address = Optional.of(Instancio.of(Address.class).create());
		Address res = address.get();
		doNothing().when(addressRepository).delete(any(Address.class));
		
		addressService.delete(res);
		// Assert: Verify the method was called once but didn´t execute
        verify(addressRepository, times(1)).delete(any(Address.class));
	}

}
