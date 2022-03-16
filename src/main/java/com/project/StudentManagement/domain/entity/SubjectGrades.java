package com.project.StudentManagement.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.StudentManagement.domain.reference.Semesters;
import com.project.StudentManagement.domain.reference.Subjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "subjectgrades")
public class SubjectGrades {

    @Id
    private Long id;

    private Subjects subject;

    private Double grade;

    private Semesters semester;

    private int academicYear;

}
