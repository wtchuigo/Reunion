package com.wtchuigo.reunion.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.wtchuigo.reunion.model.Address;
import com.wtchuigo.reunion.repositories.AddressRepository;
import com.wtchuigo.reunion.repositories.MemberRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase implements CommandLineRunner {
	
	private final MemberRepository memberRepository;
	
	private final AddressRepository addressRepository;

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	Address address = Address.builder().city("Rastatt").country("Deutschland").street("Poststraße").postalCode("76437")
			.build();

	
	

	@Override
	public void run(String... args) throws Exception {
//		log.info("Preloading " + addressRepository.save(address));
//		log.info("Preloading " + memberRepository.save(member1));
//		memberRepository.delete(member1);

	}

}
