package com.wtchuigo.reunion.mapper;

import org.mapstruct.Mapper;

import com.wtchuigo.reunion.core.MemberDto;
import com.wtchuigo.reunion.model.Member;

@Mapper
public interface MemberMapper {
	
	MemberDto memberToMemberDto(Member member);
	
	Member memberDtoToMember(MemberDto memberDto);
	

}
