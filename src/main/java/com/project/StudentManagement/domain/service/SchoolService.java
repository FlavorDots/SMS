package com.project.StudentManagement.domain.service;

import com.project.StudentManagement.domain.dto.SchoolDTO;
import com.project.StudentManagement.domain.dto.StudentDTO;

import java.util.List;

public interface SchoolService {

    SchoolDTO view(Long id) throws Exception;

    List<SchoolDTO> list();

    SchoolDTO create(SchoolDTO schoolDTO) throws Exception;

    SchoolDTO update(SchoolDTO schoolDTO) throws Exception;

    void delete(Long id) throws Exception;

    List<StudentDTO> showStudents(String code) throws Exception;
}
