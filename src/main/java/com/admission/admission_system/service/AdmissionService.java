package com.admission.admission_system.service;

import com.admission.admission_system.model.Application;
import com.admission.admission_system.model.Course;
import com.admission.admission_system.model.User;
import com.admission.admission_system.repository.ApplicationRepository;
import com.admission.admission_system.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdmissionService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Application submitApplication(Application application) {
        application.setApplicationStatus("APPLIED");
        return applicationRepository.save(application);
    }

    public List<Application> getStudentApplications(User student) {
        return applicationRepository.findByStudent(student);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Transactional
    public Application processAllocation(Long applicationId) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!"APPLIED".equals(app.getApplicationStatus())) {
            return app;
        }

        // Sequential Preference Check
        String[] preferences = {app.getPreference1(), app.getPreference2(), app.getPreference3()};
        
        for (String pref : preferences) {
            if (pref == null || pref.isEmpty()) continue;
            
            Optional<Course> courseOpt = courseRepository.findByCourseName(pref);
            if (courseOpt.isPresent()) {
                Course course = courseOpt.get();
                if (course.getAvailableSeats() > 0) {
                    // Allocate Seat
                    course.setAvailableSeats(course.getAvailableSeats() - 1);
                    courseRepository.save(course);
                    
                    app.setApplicationStatus("APPROVED");
                    app.setAllocatedCourse(course.getCourseName());
                    return applicationRepository.save(app);
                }
            }
        }

        // If no preferences met
        app.setApplicationStatus("REJECTED");
        return applicationRepository.save(app);
    }
    
    public Application updateStatus(Long id, String status) {
        Application app = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setApplicationStatus(status);
        return applicationRepository.save(app);
    }
}
