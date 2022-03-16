package com.project.StudentManagement.domain.service.impl;

import com.project.StudentManagement.domain.dao.SubjectGradesRepository;
import com.project.StudentManagement.domain.dto.StudentDTO;
import com.project.StudentManagement.domain.dto.SubjectGradesDTO;
import com.project.StudentManagement.domain.entity.Student;
import com.project.StudentManagement.domain.entity.SubjectGrades;
import com.project.StudentManagement.domain.dao.StudentRepository;
import com.project.StudentManagement.domain.identity.StudentIdentity;
import com.project.StudentManagement.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<StudentDTO> view(Long studentNumber) {
        Student student = studentRepository.findByIdStudentNumber(studentNumber)
                .orElseThrow(() -> new IllegalStateException("Student #" + studentNumber + " doesn't exist!"));

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setStudentNumber(student.getStudentId().getStudentNumberID());
        studentDTO.setMaskName(student.getMaskName());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setMiddleName(student.getMiddleName());
        studentDTO.setSubjectsGrades(student.getSubjectGrades());

        List<StudentDTO> viewStudent = new ArrayList<>();
        viewStudent.add(studentDTO);

        return viewStudent;
    }

    @Override
    public List<StudentDTO> list() {
        return studentRepository.findAll().stream().map(student -> {
            StudentDTO dto = new StudentDTO();
            dto.setStudentId(student.getStudentId());
            dto.setStudentNumber(student.getStudentId().getStudentNumberID());
            dto.setMaskName(student.getMaskName());
            dto.setFirstName(student.getFirstName());
            dto.setLastName(student.getLastName());
            dto.setMiddleName(student.getMiddleName());
            dto.setSubjectsGrades(student.getSubjectGrades());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public StudentDTO create(StudentDTO studentDTO) throws Exception {
        boolean isPresent = Optional.ofNullable(studentRepository.findByIdStudentNumber(studentDTO.getStudentNumber())).isPresent();

        if (isPresent) throw new Exception("Student #" + studentDTO.getStudentId().getStudentNumberID() + " already exists");
        else {
            Student student = new Student();
//            student.setStudentNumber(studentDTO.getStudentNumber());
            student.setMaskName(studentDTO.getMaskName());
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setMiddleName(studentDTO.getMiddleName());
            student.setSchool(studentDTO.getSchool());

            student = studentRepository.save(
                    new Student(new StudentIdentity(studentDTO.getStudentNumber(), "sa"),
                            studentDTO.getMaskName(),
                            studentDTO.getFirstName(),
                            studentDTO.getLastName(),
                            studentDTO.getMiddleName(),
                            studentDTO.getSchool())
            );
            studentDTO.setStudentId(student.getStudentId());
        }
        return studentDTO;
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) throws Exception {
        Student student = studentRepository.findByIdStudentNumber(studentDTO.getStudentId().getStudentNumberID())
                .orElseThrow(() -> new Exception("Student doesn't exist!"));

        if (studentDTO.getMaskName() != null) student.setMaskName(studentDTO.getMaskName());
        if (studentDTO.getFirstName() != null) student.setFirstName(studentDTO.getFirstName());
        if (studentDTO.getLastName() != null) student.setLastName(studentDTO.getLastName());
        if (studentDTO.getMiddleName() != null) student.setMiddleName(studentDTO.getMiddleName());
        if (studentDTO.getSchool() != null) student.setSchool(studentDTO.getSchool());

        final Student studentUpdated = studentRepository.save(student);
        studentDTO.setStudentId(studentUpdated.getStudentId());
        return studentDTO;
    }

    @Override
    public StudentDTO delete(Long studentNumber) throws Exception {
        Optional<Student> student = studentRepository.findByIdStudentNumber(studentNumber);

        Student toDelete = student.isPresent() ? student.get() :
                student.orElseThrow(() -> new Exception("Student not found!"));

        studentRepository.delete(toDelete);
        return null;
    }

    @Override
    public Student addBuddy(String buddyMask, String userMask) throws Exception {
        Optional<Student> studentBuddy = studentRepository.findByMaskName(buddyMask);
        Optional<Student> userStudent = studentRepository.findByMaskName(userMask);

        Student buddyAdd = studentBuddy.isPresent() ? studentBuddy.get() :
                studentBuddy.orElseThrow(() -> new Exception("Buddy doesn't exist!"));

        Student userAdd = userStudent.isPresent() ? userStudent.get() :
                userStudent.orElseThrow(() -> new Exception("User doesn't exist!"));

        userAdd.addBuddy(buddyAdd);

        return buddyAdd;
    }

    @Override
    public Student removeBuddy(String buddyMask, String userMask) throws Exception {
        Optional<Student> studentBuddy = studentRepository.findByMaskName(buddyMask);
        Optional<Student> userStudent = studentRepository.findByMaskName(userMask);

        Student buddyAdd = studentBuddy.isPresent() ? studentBuddy.get() :
                studentBuddy.orElseThrow(() -> new Exception("Buddy doesn't exist!"));

        Student userAdd = userStudent.isPresent() ? userStudent.get() :
                userStudent.orElseThrow(() -> new Exception("User doesn't exist!"));

        userAdd.removeBuddy(buddyAdd);

        return buddyAdd;
    }

    @Override
    public SubjectGrades addSubjectGrades(SubjectGradesDTO subjectGradesDTO, Long studentNumber) throws Exception {
        Student student = studentRepository.findByIdStudentNumber(studentNumber)
                .orElseThrow(() -> new IllegalStateException("Student #" + studentNumber + " doesn't exists!"));

        SubjectGrades subjectGrades = new SubjectGrades();
        subjectGrades.setSubject(subjectGradesDTO.getSubject());
        subjectGrades.setGrade(subjectGradesDTO.getGrade());
        subjectGrades.setSemester(subjectGradesDTO.getSemester());
        subjectGrades.setAcademicYear(subjectGradesDTO.getAcademicYear());

        List<SubjectGrades> list = new ArrayList<>();
        list.add(subjectGrades);
        student.setSubjectGrades(list);
        return subjectGrades;
    }

    //More like of an update
    @Override
    public Boolean removeSubjectGrades(Long subjectGradeId, Long studentNumber) throws Exception {
        Student student = studentRepository.findByIdStudentNumber(studentNumber)
                .orElseThrow(() -> new Exception("Student cannot be found"));

        for (SubjectGrades subjectGrades1 : student.getSubjectGrades()) {
            if (subjectGrades1.getId() == subjectGradeId) {
                student.getSubjectGrades().remove(subjectGrades1);
                studentRepository.save(student);
                return true;
            }
        }
        return false;
    }


}
