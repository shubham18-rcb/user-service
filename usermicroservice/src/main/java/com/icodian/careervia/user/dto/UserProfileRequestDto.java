package com.icodian.careervia.user.dto;

import lombok.Data;

@Data
public class UserProfileRequestDTO {
	
	private String email;
	private String password;
	
	private String firstName;
	private String lastName;
	private String phone;
	private String location;
	private String education;
	private String experience;
	private String bio;
	

}
