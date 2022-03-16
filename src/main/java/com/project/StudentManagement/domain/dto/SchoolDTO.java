package com.project.StudentManagement.domain.dto;

import com.project.StudentManagement.domain.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class SchoolDTO {

    private Long id;

    private String code;

    private String name;

    private String address;

    private List<Student> student;
}
