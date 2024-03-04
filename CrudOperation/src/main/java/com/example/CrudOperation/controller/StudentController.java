package com.example.CrudOperation.controller;

import com.example.CrudOperation.model.dto.StudentDto;
import com.example.CrudOperation.model.entity.Student;
import com.example.CrudOperation.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class StudentController {
    @Autowired
    private StudentService studentService;           //BEAN: make an object(studentService) of interface(StudentRepo) by DI

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students/add")                        //localhost:8081/students/add       //create new student.
    public Student createStudent(@RequestBody @Valid StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @GetMapping("/students")       //localhost:8081/students?pageNo=0&pageSize=5          //return first 5 students.
    public List<StudentDto> getAllStudents(@RequestParam int pageNo, @RequestParam int pageSize) {
        return studentService.getAllStudents(pageNo, pageSize);
    }

    @GetMapping("/students/{id}")                   //localhost:8081/students/1       //return student by ids.
    public StudentDto getStudent(@Valid @PathVariable int id) {
        return studentService.getStudent(id);
    }

    @PutMapping("/students/update")                     //localhost:8081/students/update       //update students.
    public StudentDto updateStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.updateStudent((studentDto));
    }

    @DeleteMapping("/students/delete/{id}")                     //localhost:8080/add       //delete student by id.
    public void deleteStudent(@Valid @PathVariable int id) {
        studentService.deleteStudent(id);
    }
}
