package com.project.StudentManagement.domain.service.impl;

import com.project.StudentManagement.domain.dao.SchoolRepository;
import com.project.StudentManagement.domain.dto.SchoolDTO;
import com.project.StudentManagement.domain.dto.StudentDTO;
import com.project.StudentManagement.domain.entity.School;
import com.project.StudentManagement.domain.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolRepository schoolRepository;


    @Override
    public SchoolDTO view(Long id) throws Exception {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new Exception("School non-existent!"));

        SchoolDTO schoolDTO = new SchoolDTO();

        schoolDTO.setId(school.getId());
        schoolDTO.setCode(school.getCode());
        schoolDTO.setName(school.getName());
        schoolDTO.setAddress(school.getAddress());
        schoolDTO.setStudent(school.getStudent());

        return schoolDTO;
    }

    @Override
    public List<SchoolDTO> list() {
        return schoolRepository.findAll().stream().map(school -> {
            SchoolDTO dto = new SchoolDTO();
            dto.setId(school.getId());
            dto.setCode(school.getCode());
            dto.setName(school.getName());
            dto.setAddress(school.getAddress());
            dto.setStudent(school.getStudent());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public SchoolDTO create(SchoolDTO schoolDTO) throws Exception {
        Optional<School> school = Optional.ofNullable(schoolRepository.findById(schoolDTO.getId())
                .orElseThrow(() -> new Exception("Doesn't Exist")));

        if (school.isPresent()) {
            School school1 = school.get();

            SchoolDTO createSchool = new SchoolDTO();

            createSchool.setCode(school1.getCode());
            createSchool.setName(school1.getName());
            createSchool.setAddress(school1.getAddress());
            createSchool.setStudent(school1.getStudent());

            return createSchool;
        }

        return null;
    }

    @Override
    public SchoolDTO update(SchoolDTO schoolDTO) throws Exception {
        Optional<School> school = Optional.ofNullable(schoolRepository.findById(schoolDTO.getId())
                .orElseThrow(() -> new Exception("Cannot be found")));

        School updateSchool = school.get();

        if (schoolDTO.getName() != null) updateSchool.setName(schoolDTO.getName());
        if (schoolDTO.getAddress() != null) updateSchool.setAddress(schoolDTO.getAddress());

        final School schoolUpdated = schoolRepository.save(updateSchool);
        schoolDTO.setId(schoolUpdated.getId());

        return schoolDTO;
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<School> school = schoolRepository.findById(id);

        School deleteSchool =
                school.isPresent() ? school.get() :
                        school.orElseThrow(() -> new Exception("Not found"));

        schoolRepository.delete(deleteSchool);
    }

    @Override
    public List<StudentDTO> showStudents(String code) throws Exception {
        Optional<School> school = schoolRepository.findByCode(code);

        School school1 = school.isPresent() ?
                school.get() :
                school.orElseThrow(() -> new Exception("Not Found"));

        return school1.getStudent().stream().map(s -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentNumber(s.getStudentNumber());
            studentDTO.setMaskName(s.getMaskName());
            studentDTO.setFirstName(s.getFirstName());
            studentDTO.setLastName(s.getLastName());
            studentDTO.setMiddleName(s.getMiddleName());
            studentDTO.setSchool(s.getSchool());
            return studentDTO;
        }).collect(Collectors.toList());
    }
}
