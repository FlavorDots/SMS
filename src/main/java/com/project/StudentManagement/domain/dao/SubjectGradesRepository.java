package com.project.StudentManagement.domain.dao;

import com.project.StudentManagement.domain.entity.SubjectGrades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectGradesRepository extends
        JpaRepository<SubjectGrades, Long> {


}
