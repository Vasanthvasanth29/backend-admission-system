package com.admission.admission_system.controller;

import com.admission.admission_system.model.Application;
import com.admission.admission_system.model.User;
import com.admission.admission_system.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {
    @Autowired
    private AdmissionService admissionService;

    @PostMapping
    public Application submitApplication(@RequestBody Application application) {
        return admissionService.submitApplication(application);
    }

    @PostMapping("/student")
    public List<Application> getStudentApplications(@RequestBody User student) {
        return admissionService.getStudentApplications(student);
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return admissionService.getAllApplications();
    }

    @PutMapping("/{id}/allocate")
    public Application allocateSeat(@PathVariable Long id) {
        return admissionService.processAllocation(id);
    }

    @PutMapping("/{id}/status")
    public Application updateStatus(@PathVariable Long id, @RequestParam String status) {
        return admissionService.updateStatus(id, status);
    }
}
