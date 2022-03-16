package com.project.StudentManagement.domain.service;

import com.project.StudentManagement.domain.dto.StudentDTO;
import com.project.StudentManagement.domain.dto.SubjectGradesDTO;
import com.project.StudentManagement.domain.entity.Student;
import com.project.StudentManagement.domain.entity.SubjectGrades;

import java.util.List;

public interface StudentService {

    List<StudentDTO> view(Long studentNumber);

    List<StudentDTO> list();

    StudentDTO create(StudentDTO studentDTO) throws Exception;

    StudentDTO update(StudentDTO studentDTO) throws Exception;

    StudentDTO delete(Long studentNumber) throws Exception;

    Student addBuddy(String buddyMask, String userMask) throws Exception;

    Student removeBuddy(String buddyMask, String userMask) throws Exception;

    SubjectGrades addSubjectGrades(SubjectGradesDTO subjectGradesDTO, Long studentNumber) throws Exception;

    Boolean removeSubjectGrades(Long subjectGradeId, Long studentNumber) throws Exception;

}
