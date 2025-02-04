package com.wtchuigo.reunion.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.wtchuigo.reunion.exceptions.ResourceNotFoundException;
import com.wtchuigo.reunion.model.User;
import com.wtchuigo.reunion.repositories.UserRepository;
import com.wtchuigo.reunion.services.MemberService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReunionController {

	private final MemberService memberService;
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return ResponseEntity.badRequest().body("Username already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return ResponseEntity.ok(userRepository.save(user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			return ResponseEntity.ok("Login successfull");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	@GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fetchMembers() {
		Map<String, Object> model = new HashMap<String, Object>();
		List<MemberDto> memberdto = memberService.findAll();
		if (!memberdto.isEmpty()) {
			model.put("status", 1);
			model.put("data", memberdto);
			return new ResponseEntity<>(model, HttpStatus.OK);
		} else {
			model.clear();
			model.put("status", 0);
			model.put("message", "Data is not found");
			return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/members/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<?> createMember(@RequestBody MemberModel memberModel) {
		Map<String, Object> model = new HashMap<String, Object>();

		AddressDto addressDto = AddressDto.builder().city(memberModel.getCity()).country(memberModel.getCountry())
				.street(memberModel.getStreet()).postalCode(memberModel.getPostalCode()).build();

		MemberDto memberDto = MemberDto.builder().firstName(memberModel.getFirstName())
				.lastName(memberModel.getLastName()).hostingDate(memberModel.getHostingDate())
				.birthdate(memberModel.getBirthdate()).email(memberModel.getEmail()).address(addressDto)
				.phone(memberModel.getPhone()).role(memberModel.getRole()).build();

		memberService.save(memberDto);
		model.put("status", 1);
		model.put("message", "Record is Saved Successfully!");
		return new ResponseEntity<>(model, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/members/delete/{Member-ID}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<?> deleteMember(@PathVariable(required = true, name = "Member-ID") int id) {
		Map<String, Object> body = new HashMap<String, Object>();
		if (!memberService.existsById(id)) {
			throw new ResourceNotFoundException("No member found with the id: " + id);
		}
		memberService.delete(id);
		body.put("status", 1);
		body.put("message", "Record is deleted Successfully!");
		return new ResponseEntity<>(body, HttpStatus.MOVED_PERMANENTLY);
	}
}
