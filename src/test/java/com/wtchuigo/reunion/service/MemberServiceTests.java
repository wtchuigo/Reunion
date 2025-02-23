package com.wtchuigo.reunion.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.wtchuigo.reunion.BaseTest;
import com.wtchuigo.reunion.core.MemberDto;
import com.wtchuigo.reunion.mapper.MemberMapper;
import com.wtchuigo.reunion.model.Member;
import com.wtchuigo.reunion.repositories.MemberRepository;
import com.wtchuigo.reunion.services.MemberService;

@SpringBootTest
public class MemberServiceTests extends BaseTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberMapper memberMapper;

	@MockBean
	MemberRepository memberRepository;

	@Test
	void testFindAll() {
		List<Member> members = Instancio.ofList(Member.class).create();
		when(memberRepository.findAll()).thenReturn(members);
		List<MemberDto> result = memberService.findAll();
		List<MemberDto> memerdto = new ArrayList<MemberDto>();
		Optional.ofNullable(members).ifPresent(
				list -> list.stream().forEach(member -> memerdto.add(memberMapper.memberToMemberDto(member))));
		assertThat(result.size()).isEqualTo(memerdto.size());
		Assertions.assertTrue(result.containsAll(memerdto));
		
	}
	
	@Test
	void testSave() {
		Optional<Member> member = Optional.of(Instancio.of(Member.class).create());
		Member res = member.get();
		when(memberRepository.save(any(Member.class))).thenReturn(res);
		
		memberService.save(memberMapper.memberToMemberDto(res));
		// Assert: Verify the method was called once
        verify(memberRepository, times(1)).save(any(Member.class));
	}
	
	@Test
	void testDelete() {
		doNothing().when(memberRepository).deleteById(anyInt());
		
		memberService.delete(anyInt());
		// Assert: Verify the method was called once but didn´t execute
        verify(memberRepository, times(1)).deleteById(anyInt());
	}
	
	@Test
	void testExistsById() {
		when(memberRepository.existsById(anyInt())).thenReturn(true);
		
		Assertions.assertTrue(memberService.existsById(anyInt()));
		// Assert: Verify the method was called once
        verify(memberRepository, times(1)).existsById(anyInt());
	}

}
