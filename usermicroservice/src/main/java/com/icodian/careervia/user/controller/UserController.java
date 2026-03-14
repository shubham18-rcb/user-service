																																																																																								package com.icodian.careervia.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icodian.careervia.user.dto.LoginRequestDTO;
import com.icodian.careervia.user.dto.LoginResponseDTO;
import com.icodian.careervia.user.dto.UserCreateRequestDTO;
import com.icodian.careervia.user.dto.UserProfileDTO;
import com.icodian.careervia.user.dto.UserProfileRequestDTO;
import com.icodian.careervia.user.dto.UserResponseDTO;
import com.icodian.careervia.user.dto.UserUpdateRequestDTO;
import com.icodian.careervia.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// THIS IS USED FOR CREATING A NEW USER
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateRequestDTO request) {
		return ResponseEntity.ok(userService.createUser(request));
	}

	// THIS IS USED TO LOGIN THE USER
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
		return ResponseEntity.ok(userService.login(request));
	}

	// GET A USER BY USER's ID
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	// GET A USER BY USER's EMAIL
	@GetMapping
	public ResponseEntity<UserResponseDTO> getUserByEmail(@RequestParam String email) {
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}

	// UPDATE THE USER INFO
	@PutMapping("/{userId}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,
			@RequestBody UserUpdateRequestDTO request) {
		return ResponseEntity.ok(userService.updateUser(userId, request));
	}

	// DELETE THE ALREADY EXISTING USER
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok("User deleted successfully");
	}

	// GET THE USER PROFILE
	@GetMapping("/{userId}/profile")
	public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserProfile(userId));
	}

	// CREATE OR UPDATE USER PROFILE
	@PutMapping("/{userId}/profile")
	public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long userId,
			@RequestBody UserProfileRequestDTO request) {
		return ResponseEntity.ok(userService.createOrUpdateProfile(userId, request));
	}

}
