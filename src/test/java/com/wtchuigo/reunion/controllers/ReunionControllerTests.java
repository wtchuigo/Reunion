//package com.wtchuigo.reunion.controllers;
//
//import org.instancio.Instancio;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.wtchuigo.reunion.BaseTest;
//import com.wtchuigo.reunion.model.User;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ReunionControllerTests extends BaseTest {
//
//	@Autowired
//	ReunionController reunionController;
//	@Autowired
//	MockMvc mvc;
//
//	@Test
//	void testRegisterUser() throws Exception {
//		User user = User.builder().id(1L).username("test").password("test").role(" ").build();		
//		mvc.perform(MockMvcRequestBuilders.post("/api/v1/register")
//				.content(asJsonString(user))
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.content().string("User successfully added!"));
//
//	}
//	
//	public static String asJsonString(final Object obj) {
//	    try {
//	        return new ObjectMapper().writeValueAsString(obj);
//	    } catch (Exception e) {
//	        throw new RuntimeException(e);
//	    }
//	}
//
//}
