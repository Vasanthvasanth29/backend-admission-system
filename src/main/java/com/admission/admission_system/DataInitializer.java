package com.admission.admission_system;

import com.admission.admission_system.model.Course;
import com.admission.admission_system.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public DataInitializer(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (courseRepository.count() == 0) {
            List<Course> defaultCourses = Arrays.asList(
                new Course(null, "Computer Science Engineering", "B.E", 4, 60, 60),
                new Course(null, "Mechanical Engineering", "B.E", 4, 60, 60),
                new Course(null, "Civil Engineering", "B.E", 4, 60, 60),
                new Course(null, "Electronics and Communication Engineering", "B.E", 4, 60, 60),
                new Course(null, "Information Technology", "B.Tech", 4, 60, 60),
                new Course(null, "Artificial Intelligence & Data Science", "B.Tech", 4, 60, 60)
            );
            courseRepository.saveAll(defaultCourses);
            System.out.println("Default engineering courses initialized!");
        }
    }
}
