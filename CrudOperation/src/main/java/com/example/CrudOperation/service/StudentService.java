package com.example.CrudOperation.service;

import com.example.CrudOperation.model.dto.StudentDto;
import com.example.CrudOperation.model.entity.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents(int pageNo ,int pageSize);

    StudentDto getStudent(int id);

    StudentDto updateStudent(StudentDto studentDto);

    void deleteStudent(int id);
}