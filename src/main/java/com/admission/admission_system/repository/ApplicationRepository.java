package com.admission.admission_system.repository;

import com.admission.admission_system.model.Application;
import com.admission.admission_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudent(User student);
}
