package com.icodian.careervia.user.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.icodian.careervia.user.entity.User;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

  
    private User createUser() {
        User user = new User();
        user.setEmail("shubham@gmail.com");
        user.setPassword("12345");   
        user.setPhone("1234567890"); 
        return user;
    }

    @Test
    public void testSaveUser() {
        User user = createUser();

        User saved = userRepository.save(user);

        assertNotNull(saved.getUserId());
    }

    @Test
    void testFindByEmail() {
        User user = createUser(); 

        userRepository.save(user);

        Optional<User> result = userRepository.findByEmail("shubham@gmail.com");

        assertTrue(result.isPresent());
    }
}