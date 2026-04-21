package com.icodian.careervia.user.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.icodian.careervia.user.entity.User;
import com.icodian.careervia.user.entity.Course;
import com.icodian.careervia.user.entity.UserCourse;

import jakarta.persistence.EntityManager;

@DataJpaTest
class UserCourseRepositoryTest {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldReturnCoursesByUserId() {

    	//creating a persist user
        User user = new User();
        user.setPassword("123456");
        entityManager.persist(user);

        //creating a new persist course1
        Course course1 = new Course();
        course1.setCourseName("Java");
        entityManager.persist(course1);

        //creating a new persist course2
        Course course2 = new Course();
        course2.setCourseName("Spring Boot");
        entityManager.persist(course2);

        //Create UserCourse entries
        UserCourse uc1 = new UserCourse();
        uc1.setUser(user);
        uc1.setCourse(course1);
        uc1.setProgress(50);
        uc1.setCompletionStatus("IN_PROGRESS");

        UserCourse uc2 = new UserCourse();
        uc2.setUser(user);
        uc2.setCourse(course2);
        uc2.setProgress(100);
        uc2.setCompletionStatus("COMPLETED");
        

        entityManager.persist(uc1);
        entityManager.persist(uc2);

        entityManager.flush();

        // Call repository
        Long userId = user.getUserId();
        List<UserCourse> result = userCourseRepository.findByUserUserId(userId);

        // Assertions (checking the results)
        assertThat(result).hasSize(2);
        assertThat(result)
            .extracting(uc -> uc.getCourse().getCourseName())
            .containsExactlyInAnyOrder("Java", "Spring Boot");
    }
}