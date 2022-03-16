package com.project.StudentManagement.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.StudentManagement.domain.identity.StudentIdentity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "student")
public class Student {

    @EmbeddedId
    @Column(name = "ID")
    private StudentIdentity studentId; //studentnumber + schoolcode

//    @Column(unique = true, nullable = false)
//    private Long studentNumber;

    @Column(name = "MASK_NAME", unique = true, nullable = false)
    private String maskName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @OneToMany
    private List<SubjectGrades> subjectGrades = new ArrayList<>();

    @Column(name = "BUDDY")
    @ManyToMany(mappedBy = "individual")
    private Set<Student> buddies = new HashSet<>();

//    @JoinTable(name = "buddies",
//    joinColumns = {@JoinColumn(name = "ID")},
//            inverseJoinColumns = {@JoinColumn(name = "BUDDY")}
//    )
    @ManyToMany
    private Set<Student> individual = new HashSet<>();


    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "school_id")
    private School school;

    public Student(StudentIdentity studentId, String maskName, String firstName, String lastName, String middleName, School school) {
        this.studentId = studentId;
        this.maskName = maskName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.school = school;
    }

    public void addBuddy(Student student){
        individual.add(student);
        student.getBuddies().add(this);
    }

    public void removeBuddy(Student student){
        individual.remove(student);
        student.getBuddies().remove(this);
    }
}
