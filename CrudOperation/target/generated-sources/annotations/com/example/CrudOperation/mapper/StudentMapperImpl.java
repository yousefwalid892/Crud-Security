package com.example.CrudOperation.mapper;

import com.example.CrudOperation.model.dto.StudentDto;
import com.example.CrudOperation.model.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-29T11:59:26+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDto toDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDto.StudentDtoBuilder studentDto = StudentDto.builder();

        studentDto.id( student.getId() );
        studentDto.firstName( student.getFirstName() );
        studentDto.lastName( student.getLastName() );
        studentDto.job( student.getJob() );
        studentDto.age( student.getAge() );
        studentDto.email( student.getEmail() );

        return studentDto.build();
    }

    @Override
    public Student toEntity(StudentDto studentDto) {
        if ( studentDto == null ) {
            return null;
        }

        Student.StudentBuilder student = Student.builder();

        student.id( studentDto.getId() );
        student.firstName( studentDto.getFirstName() );
        student.lastName( studentDto.getLastName() );
        student.job( studentDto.getJob() );
        student.age( studentDto.getAge() );
        student.email( studentDto.getEmail() );

        return student.build();
    }
}
