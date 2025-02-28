package com.wtchuigo.reunion.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wtchuigo.reunion.core.AddressDto;
import com.wtchuigo.reunion.core.MemberDto;
import com.wtchuigo.reunion.core.MemberModel;
import com.wtchuigo.reunion.core.UserDto;
import com.wtchuigo.reunion.mapper.UserMapper;
import com.wtchuigo.reunion.services.MemberService;
import com.wtchuigo.reunion.services.UserService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReunionController {

	private final MemberService memberService;
	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	private final UserMapper userMapper;
	

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerUser(@RequestBody UserDto user) {
		userService.saveUser(userMapper.userDtoToUser(user));
		return ResponseEntity.status(HttpStatus.CREATED).body("User successfully added!");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody UserDto user) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			return ResponseEntity.status(HttpStatus.OK).body("Login successfull");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	@GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MemberDto>> fetchMembers() {
		List<MemberDto> memberdtoList = memberService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(memberdtoList);
	}

	@PostMapping(value = "/members/create", produces = "text/plain")
	public ResponseEntity<String> createMember(@RequestBody MemberModel memberModel) {

		AddressDto addressDto = AddressDto.builder().city(memberModel.getCity()).country(memberModel.getCountry())
				.street(memberModel.getStreet()).postalCode(memberModel.getPostalCode()).build();

		MemberDto memberDto = MemberDto.builder().firstName(memberModel.getFirstName())
				.lastName(memberModel.getLastName()).hostingDate(memberModel.getHostingDate())
				.birthdate(memberModel.getBirthdate()).email(memberModel.getEmail()).address(addressDto)
				.phone(memberModel.getPhone()).role(memberModel.getRole()).build();

		memberService.save(memberDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Member is Saved Successfully!");
	}

	@DeleteMapping(value = "/members/delete/{id}", produces = "text/plain")
	public ResponseEntity<String> deleteMember(@PathVariable int id) {
		memberService.delete(id);
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body("Member is deleted Successfully!");
	}
}
