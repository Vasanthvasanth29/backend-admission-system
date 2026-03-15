package com.admission.admission_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
    
    private String preference1;
    private String preference2;
    private String preference3;
    
    private double cutoffMark;
    private String applicationStatus; // APPLIED, APPROVED, REJECTED, WAITLISTED
    
    private String allocatedCourse; // The course eventually allocated

    public Application() {}

    public Application(Long id, User student, String preference1, String preference2, String preference3, double cutoffMark, String applicationStatus, String allocatedCourse) {
        this.id = id;
        this.student = student;
        this.preference1 = preference1;
        this.preference2 = preference2;
        this.preference3 = preference3;
        this.cutoffMark = cutoffMark;
        this.applicationStatus = applicationStatus;
        this.allocatedCourse = allocatedCourse;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public String getPreference1() { return preference1; }
    public void setPreference1(String preference1) { this.preference1 = preference1; }

    public String getPreference2() { return preference2; }
    public void setPreference2(String preference2) { this.preference2 = preference2; }

    public String getPreference3() { return preference3; }
    public void setPreference3(String preference3) { this.preference3 = preference3; }

    public double getCutoffMark() { return cutoffMark; }
    public void setCutoffMark(double cutoffMark) { this.cutoffMark = cutoffMark; }

    public String getApplicationStatus() { return applicationStatus; }
    public void setApplicationStatus(String applicationStatus) { this.applicationStatus = applicationStatus; }

    public String getAllocatedCourse() { return allocatedCourse; }
    public void setAllocatedCourse(String allocatedCourse) { this.allocatedCourse = allocatedCourse; }
}