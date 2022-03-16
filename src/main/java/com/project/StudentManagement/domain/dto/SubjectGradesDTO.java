package com.project.StudentManagement.domain.dto;

import com.project.StudentManagement.domain.reference.Semesters;
import com.project.StudentManagement.domain.reference.Subjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class SubjectGradesDTO {

    private Long id;

    private Subjects subject;

    private Double grade;

    private Semesters semester;

    private int academicYear;

}
