package com.wtchuigo.reunion.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wtchuigo.reunion.BaseTest;
import com.wtchuigo.reunion.core.MemberDto;
import com.wtchuigo.reunion.core.MemberModel;
import com.wtchuigo.reunion.core.UserDto;
import com.wtchuigo.reunion.model.User;
import com.wtchuigo.reunion.services.MemberService;
import com.wtchuigo.reunion.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class ReunionControllerTests extends BaseTest {

	@Autowired
	MockMvc mvc;
	@MockBean
	UserService userService;
	@MockBean
	MemberService memberService;
	@Autowired
	ReunionController reunionController;

	@Test
	void testRegisterUser() throws Exception {
		UserDto user = Instancio.of(UserDto.class).create();
		doNothing().when(userService).saveUser(any(User.class));
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/register").content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string("User successfully added!"));
		// Assert: Verify the method was called once
		verify(userService, times(1)).saveUser(any(User.class));

	}

	@Test
	void testFetchMembers() throws Exception {
		List<MemberDto> memberDtolist = Instancio.ofList(MemberDto.class).create();
		when(memberService.findAll()).thenReturn(memberDtolist);
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/members").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(asJsonString(memberDtolist)));
		// Assert: Verify the method was called once
		verify(memberService, times(1)).findAll();
	}

	@Test
	void testCreateMember() throws Exception {
		MemberModel memberModel = Instancio.of(MemberModel.class).create();
		doNothing().when(memberService).save(any(MemberDto.class));

		mvc.perform(MockMvcRequestBuilders.post("/api/v1/members/create").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(memberModel)).accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string("Member is Saved Successfully!"));
		// Assert: Verify the method was called once
		verify(memberService, times(1)).save(any(MemberDto.class));
	}

	@Test
	void testDeleteMember() throws Exception {
		doNothing().when(memberService).delete(anyInt());
		mvc.perform(MockMvcRequestBuilders.delete("/api/v1/members/delete/1")
				.accept(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.status().isMovedPermanently())
				.andExpect(MockMvcResultMatchers.content().string("Member is deleted Successfully!"));
		// Assert: Verify the method was called once
		verify(memberService, times(1)).delete(anyInt());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
