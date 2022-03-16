package com.project.StudentManagement.domain.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.project.StudentManagement.domain.controller.StudentController;
import com.project.StudentManagement.domain.dto.StudentDTO;
import com.project.StudentManagement.domain.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentModelAssembler
        implements RepresentationModelAssembler<Student, StudentDTO> {

    @Override
    public StudentDTO toModel(Student entity) {
        ModelMapper modelMapper = new ModelMapper();
        StudentDTO studentDTO = modelMapper.map(entity, StudentDTO.class);

        Link selfLink = linkTo(methodOn(StudentController.class).getStudentByStudentNumber(entity.getStudentId().getStudentNumberID()))
                .withSelfRel();
        studentDTO.add(selfLink);

        return studentDTO;
    }

    @Override
    public CollectionModel<StudentDTO> toCollectionModel(Iterable<? extends Student> entities) {
        ModelMapper modelMapper = new ModelMapper();
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student : entities) {
            StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
            studentDTO.add(linkTo(methodOn(StudentController.class)
                    .getAllStudents()).withSelfRel());
            studentDTOS.add(studentDTO);
        }
        return CollectionModel.of(studentDTOS);
    }
}