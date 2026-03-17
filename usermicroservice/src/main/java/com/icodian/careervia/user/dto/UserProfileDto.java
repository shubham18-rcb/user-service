package com.icodian.careervia.user.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserProfileDTO {
	
	private Long profileId;
	private String email;
	private String bio;
	private String experience;
	private String education;
	private Integer profileStrength;
	private Date updatedAt;
	

}
