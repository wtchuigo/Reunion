package com.wtchuigo.reunion.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
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
import com.wtchuigo.reunion.exceptions.ReunionException;
import com.wtchuigo.reunion.mapper.MemberMapper;
import com.wtchuigo.reunion.model.Member;
import com.wtchuigo.reunion.repositories.MemberRepository;
import com.wtchuigo.reunion.services.MemberService;

@SpringBootTest
class MemberServiceTests extends BaseTest {
	
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
		assertThat(result).hasSameSizeAs(memerdto);
		Assertions.assertTrue(result.containsAll(memerdto));
		// Assert: Verify the method was called once
        verify(memberRepository, times(1)).findAll();
		
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
		when(memberRepository.existsById(anyInt())).thenReturn(true);
		doNothing().when(memberRepository).deleteById(anyInt());
		
		memberService.delete(anyInt());
		// Assert: Verify the method was called once but didn´t execute
        verify(memberRepository, times(1)).deleteById(anyInt());
	}
	
	@Test
	void testDeleteThrowReunionException() {
		when(memberRepository.existsById(anyInt())).thenReturn(false);
		
		Assertions.assertThrowsExactly(ReunionException.class, () -> memberService.delete(anyInt()));
		// Assert: Verify the method was never called
        verify(memberRepository, never()).deleteById(anyInt());
	}
	
	@Test
	void testExistsById() {
		when(memberRepository.existsById(anyInt())).thenReturn(true);
		
		Assertions.assertTrue(memberService.existsById(anyInt()));
		// Assert: Verify the method was called once
        verify(memberRepository, times(1)).existsById(anyInt());
	}

}
