package com.project.StudentManagement.domain.controller;

import com.project.StudentManagement.domain.dto.StudentDTO;
import com.project.StudentManagement.domain.entity.Student;
import com.project.StudentManagement.domain.entity.SubjectGrades;
import org.apache.coyote.Response;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface StudentController {

//    ResponseEntity<Student> getAllStudents();

    CollectionModel<StudentDTO> getAllStudents();

    StudentDTO getStudentByStudentNumber(Long studentNumber);

    ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws Exception;

    ResponseEntity<Student> updateStudent(@RequestBody StudentDTO studentDTO) throws Exception;

    Map<String, Boolean> deleteStudent(@RequestBody Student student) throws Exception;

    //Buddy System

    ResponseEntity<Student> addBuddy(Long studentNumber) throws Exception;

    Map<String, Boolean> removeBuddy(Long studentNumber) throws Exception;


    //SubjectGrades System

    ResponseEntity<SubjectGrades> addSubjectGrades(Long studentNumber) throws Exception;
}
