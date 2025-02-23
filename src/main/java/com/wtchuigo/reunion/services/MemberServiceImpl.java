package com.wtchuigo.reunion.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wtchuigo.reunion.core.MemberDto;
import com.wtchuigo.reunion.exceptions.ResourceNotFoundException;
import com.wtchuigo.reunion.mapper.MemberMapper;
import com.wtchuigo.reunion.model.Member;
import com.wtchuigo.reunion.repositories.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	private final MemberMapper memberMapper;

	@Override
	public void save(MemberDto memberDto) {
		Member member = memberMapper.memberDtoToMember(memberDto);
		member.setCreateDate(getCurrentDate());
		memberRepository.save(member);
	}

	@Override
	public void delete(int id) {
		if (!memberRepository.existsById(id)) {
			throw new ResourceNotFoundException("No member found with the id: " + id);
		}
		memberRepository.deleteById(id);
	}

	@Override
	public List<MemberDto> findAll() {
		List<Member> memberList = memberRepository.findAll();
		List<MemberDto> memerdto = new ArrayList<MemberDto>();
		Optional.ofNullable(memberList).ifPresent(
				list -> list.stream().forEach(member -> memerdto.add(memberMapper.memberToMemberDto(member))));
		return memerdto;
	}
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCurrentDate() {
	    return new Date();
	}

	@Override
	public boolean existsById(int id) {
		return memberRepository.existsById(id);
	}

}
