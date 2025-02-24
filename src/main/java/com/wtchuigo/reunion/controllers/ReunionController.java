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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wtchuigo.reunion.core.AddressDto;
import com.wtchuigo.reunion.core.MemberDto;
import com.wtchuigo.reunion.core.MemberModel;
import com.wtchuigo.reunion.model.User;
import com.wtchuigo.reunion.services.CustomUserDetailsService;
import com.wtchuigo.reunion.services.MemberService;
import com.wtchuigo.reunion.services.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReunionController {

	private final MemberService memberService;
	private final AuthenticationManager authenticationManager;
	private final UserService userService;
	

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("User successfully added!");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			return ResponseEntity.ok("Login successfull");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	@GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MemberDto>> fetchMembers() {
		List<MemberDto> memberdtoList = memberService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(memberdtoList);
	}

	@PostMapping(value = "/members/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<String> createMember(@RequestBody MemberModel memberModel) {

		AddressDto addressDto = AddressDto.builder().city(memberModel.getCity()).country(memberModel.getCountry())
				.street(memberModel.getStreet()).postalCode(memberModel.getPostalCode()).build();

		MemberDto memberDto = MemberDto.builder().firstName(memberModel.getFirstName())
				.lastName(memberModel.getLastName()).hostingDate(memberModel.getHostingDate())
				.birthdate(memberModel.getBirthdate()).email(memberModel.getEmail()).address(addressDto)
				.phone(memberModel.getPhone()).role(memberModel.getRole()).build();

		memberService.save(memberDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Record is Saved Successfully!");
	}

	@DeleteMapping(value = "/members/delete/{Member-ID}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<String> deleteMember(@PathVariable(required = true, name = "Member-ID") int id) {
		memberService.delete(id);
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body("Record is deleted Successfully!");
	}
}
