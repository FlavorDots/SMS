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
            ss.setStudentId(s.getStudentId());
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
        List<StudentDTO> student = studentService.view(studentNumber);
        Student student1 = new Student();
        student1.setStudentId(student.get(0).getStudentId());
        student1.setMaskName(student.get(0).getMaskName());
        student1.setFirstName(student.get(0).getFirstName());
        student1.setLastName(student.get(0).getLastName());
        student1.setMiddleName(student.get(0).getMiddleName());
        student1.setSubjectGrades(student.get(0).getSubjectsGrades());
        student1.setBuddies(student.get(0).getBuddies());
        student1.setIndividual(student.get(0).getIndividual());
        student1.setSchool(student.get(0).getSchool());

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
        Long studentNumber = student.getStudentId().getStudentNumberID();
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
