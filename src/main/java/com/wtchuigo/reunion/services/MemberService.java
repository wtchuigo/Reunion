package com.wtchuigo.reunion.services;

import java.util.List;

import com.wtchuigo.reunion.core.MemberDto;

public interface MemberService {
	
	void save(MemberDto memberDto);
	void delete(int id);
	List<MemberDto> findAll();
	boolean existsById(int id);

}
