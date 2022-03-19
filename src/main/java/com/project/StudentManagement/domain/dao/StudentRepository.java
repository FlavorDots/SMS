package com.project.StudentManagement.domain.dao;

import com.project.StudentManagement.domain.entity.Student;
import com.project.StudentManagement.domain.identity.StudentIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends
        JpaRepository<Student, StudentIdentity> {

    Optional<Student> findByIdStudentNumberId(Long studentNumber);

//    Boolean existsByStudentNumber(Long studentNumber);

    Optional<Student> findByMaskName(String maskName);
}
