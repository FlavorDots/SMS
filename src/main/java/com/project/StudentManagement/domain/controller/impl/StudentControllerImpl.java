package com.project.StudentManagement.domain.controller.impl;

import com.project.StudentManagement.domain.assembler.StudentModelAssembler;
import com.project.StudentManagement.domain.controller.StudentController;
import com.project.StudentManagement.domain.dto.StudentDTO;
import com.project.StudentManagement.domain.entity.Student;
import com.project.StudentManagement.domain.entity.SubjectGrades;
import com.project.StudentManagement.domain.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentControllerImpl implements StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentControllerImpl.class);

    @Autowired
    StudentService studentService;

    @Autowired
    StudentModelAssembler studentModelAssembler;

    @Override
    @GetMapping(produces = "application/hal+json")
    public CollectionModel<StudentDTO> getAllStudents() {
        List<StudentDTO> studentList = studentService.list();
        List<Student> students = new ArrayList<>();

        for (StudentDTO s : studentList){
            Student ss = new Student();
            ss.setId(s.getId());
            ss.setStudentNumber(s.getId().getStudentNumberId());
            ss.setMaskName(s.getMaskName());
            ss.setFirstName(s.getFirstName());
            ss.setLastName(s.getLastName());
            ss.setMiddleName(s.getMiddleName());
            ss.setSubjectGrades(s.getSubjectsGrades());
            ss.setBuddies(s.getBuddies());
            ss.setIndividual(s.getIndividual());
            ss.setSchool(s.getSchool());

            students.add(ss);
        }

        return studentModelAssembler.toCollectionModel(students);
    }

    @Override
    @GetMapping(value = "/{studentNumber}", produces = "application/hal+json")
    public StudentDTO getStudentByStudentNumber(@PathVariable("studentNumber") Long studentNumber) {
        StudentDTO student = studentService.view(studentNumber);
        Student student1 = new Student();
        student1.setId(student.getId());
        student1.setMaskName(student.getMaskName());
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setMiddleName(student.getMiddleName());
        student1.setSubjectGrades(student.getSubjectsGrades());
        student1.setBuddies(student.getBuddies());
        student1.setIndividual(student.getIndividual());
        student1.setSchool(student.getSchool());

        return studentModelAssembler.toModel(student1);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws Exception {
        return new ResponseEntity(studentService.create(studentDTO), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/")
    public ResponseEntity<Student> updateStudent(StudentDTO studentDTO) throws Exception {
        return new ResponseEntity(studentService.update(studentDTO), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/")
    public Map<String, Boolean> deleteStudent(@RequestBody Student student) throws Exception {
        Long studentNumber = student.getId().getStudentNumberId();
        studentService.delete(studentNumber);
        Map<String, Boolean> response = new HashMap<>();
        response.put("--Student Deleted--", Boolean.TRUE);
        return response;
    }

    @Override
    public ResponseEntity<Student> addBuddy(Long studentNumber) throws Exception {
        return null;
    }

    @Override
    public Map<String, Boolean> removeBuddy(Long studentNumber) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<SubjectGrades> addSubjectGrades(Long studentNumber) throws Exception {
        return null;
    }
}
