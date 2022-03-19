package com.project.StudentManagement.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.StudentManagement.domain.entity.School;
import com.project.StudentManagement.domain.entity.Student;
import com.project.StudentManagement.domain.entity.SubjectGrades;
import com.project.StudentManagement.domain.identity.StudentIdentity;
import com.project.StudentManagement.domain.reference.Subjects;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO extends RepresentationModel<StudentDTO> {

    private StudentIdentity id; //studentnumber + schoolcode

    private Long studentNumber;

    private String maskName;

    private String firstName;

    private String lastName;

    private String middleName;

    private List<SubjectGrades> subjectsGrades = new ArrayList<>();

    private Set<Student> buddies = new HashSet<>();

    private Set<Student> individual = new HashSet<>();

    private School school;
}
