package com.project.StudentManagement.domain.mapper;


import com.project.StudentManagement.domain.dto.StudentDTO;
import com.project.StudentManagement.domain.entity.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    public void updateStudentFromDto(StudentDTO studentDTO, @MappingTarget Student entity);
}
