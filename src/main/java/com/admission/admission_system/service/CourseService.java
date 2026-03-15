package com.admission.admission_system.service;

import com.admission.admission_system.model.Course;
import com.admission.admission_system.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) {
        course.setAvailableSeats(course.getTotalSeats());
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setCourseName(courseDetails.getCourseName());
        course.setDegreeType(courseDetails.getDegreeType());
        course.setDuration(courseDetails.getDuration());
        course.setTotalSeats(courseDetails.getTotalSeats());
        // Simple logic: if total seats increased, increase available seats too
        int diff = courseDetails.getTotalSeats() - course.getTotalSeats();
        course.setAvailableSeats(course.getAvailableSeats() + diff);
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}