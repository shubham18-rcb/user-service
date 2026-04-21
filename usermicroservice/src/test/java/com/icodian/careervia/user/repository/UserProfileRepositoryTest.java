package com.icodian.careervia.user.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.icodian.careervia.user.entity.User;
import com.icodian.careervia.user.entity.UserProfile;

@DataJpaTest
public class UserProfileRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@BeforeEach
	void setUp() {
		userProfileRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	private User createUser() {
		User user = new User();
		user.setEmail("shubham@gmail.com");
		user.setPassword("12345678");
		return user;

	}
	
	private UserProfile createProfile(User user) {
		UserProfile profile = new UserProfile();
		profile.setUser(user);
		profile.setBio("Java Full Stack Developer");
		return profile;
	}
	
	@Test
	void testSaveUserProfile() {
		User savedUser = userRepository.save(createUser());
		
		UserProfile profile = createProfile(savedUser);
		userProfileRepository.save(profile);
		
		Optional<UserProfile> result = userProfileRepository.findByUserUserId(savedUser.getUserId());
		
		assertTrue(result.isPresent());
	}
	
	
}
