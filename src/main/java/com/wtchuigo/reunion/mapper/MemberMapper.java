package com.wtchuigo.reunion.mapper;

import org.mapstruct.Mapper;

import com.wtchuigo.reunion.core.MemberDto;
import com.wtchuigo.reunion.model.Member;

@Mapper
@SuppressWarnings("java:S106")
public interface MemberMapper {
	
	MemberDto memberToMemberDto(Member member);
	
	Member memberDtoToMember(MemberDto memberDto);
	

}
