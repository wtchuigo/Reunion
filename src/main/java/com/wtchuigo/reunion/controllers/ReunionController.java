package com.wtchuigo.reunion.controllers;

import static com.wtchuigo.reunion.constants.RestEndpoints.*;
import static com.wtchuigo.reunion.model.Roles.ROLE_USER;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wtchuigo.reunion.core.AddressDto;
import com.wtchuigo.reunion.core.MemberDto;
import com.wtchuigo.reunion.core.MemberModel;
import com.wtchuigo.reunion.services.AddressService;
import com.wtchuigo.reunion.services.MemberService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
//@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReunionController {

	private final MemberService memberService;
	private final AuthenticationManager authenticationManager;
	private final AddressService addressService;
//	private final UserService userService;
//	private final UserMapper userMapper;
	

//	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<String> registerUser(@RequestBody UserDto user) {
//		userService.saveUser(userMapper.userDtoToUser(user));
//		return ResponseEntity.status(HttpStatus.CREATED).body("User successfully added!");
//	}
//	
	@PostMapping(value = LOGIN, produces = "text/plain")
	public ResponseEntity<String> loginUser(@PathVariable String email, @PathVariable String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			return ResponseEntity.status(HttpStatus.OK).body("Login successfull");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	@GetMapping(value = GET_ALL_MEMBERS, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize(ROLE_USER)
	public ResponseEntity<List<MemberDto>> getAllMembers() {
		List<MemberDto> memberdtoList = memberService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(memberdtoList);
	}

	@PostMapping(value = REGISTER, produces = "text/plain")
	public ResponseEntity<String> registerMember(@RequestBody MemberModel memberModel) {

		AddressDto addressDto = AddressDto.builder().city(memberModel.getCity()).country(memberModel.getCountry())
				.street(memberModel.getStreet()).postalCode(memberModel.getPostalCode()).build();

		MemberDto memberDto = MemberDto.builder().firstName(memberModel.getFirstName())
				.lastName(memberModel.getLastName()).hostingDate(memberModel.getHostingDate())
				.birthdate(memberModel.getBirthdate()).email(memberModel.getEmail()).address(addressDto)
				.phone(memberModel.getPhone()).role(memberModel.getRole()).gender(memberModel.getGender()).password(memberModel.getPassword()).build();

		memberService.save(memberDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Member is Saved Successfully!");
	}
	
	@PostMapping(value = UPDATE_MEMBER, produces = "text/plain")
	public ResponseEntity<String> updateMember(@RequestBody MemberDto memberDto) {
		memberService.save(memberDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Member is updated Successfully!");
	}
	
	@PostMapping(value = UPDATE_ADDRESS, produces = "text/plain")
	public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto) {
		addressService.save(addressDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Address is updated Successfully!");
	}

	@DeleteMapping(value = DELETE, produces = "text/plain")
	public ResponseEntity<String> deleteMember(@PathVariable int id) {
		memberService.delete(id);
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body("Member is deleted Successfully!");
	}
}
