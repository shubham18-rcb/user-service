package com.icodian.careervia.user.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.icodian.careervia.user.entity.Skill;
import com.icodian.careervia.user.entity.User;
import com.icodian.careervia.user.entity.UserSkill;
import com.netflix.discovery.converters.Auto;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class UserSkillRepositoryTest {
	
	@Autowired
	private UserSkillRepository userSkillRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Test
	void shouldReturnSkillsByUserId() {
		
		User user = new User();
		user.setEmail("shubham@gmail.com");
        user.setPassword("12345"); 
		entityManager.persist(user);
		
		Skill skill1 = new Skill();
		skill1.setSkillName("Java");
		entityManager.persist(skill1);
		
		Skill skill2 = new Skill();
		skill2.setSkillName("Java Script");
		entityManager.persist(skill2);
		
		UserSkill us1 = new UserSkill();
		us1.setUser(user);
		us1.setSkill(skill1);
		
		UserSkill us2 = new UserSkill();
		us2.setUser(user);
		us2.setSkill(skill2);
		
		entityManager.persist(us1);
		entityManager.persist(us2);
		
		entityManager.flush();
		
		List<UserSkill> result = userSkillRepository.findByUserUserId(user.getUserId());
		
		assertThat(result).hasSize(2);
		assertThat(result)
			.extracting(us -> us.getSkill().getSkillName())
			.containsExactlyInAnyOrder("Java", "Java Script");
	}
	
	

}
