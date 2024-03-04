
package com.example.CrudOperation.service;

import com.example.CrudOperation.model.dto.StudentDto;
import com.example.CrudOperation.model.entity.Student;
import com.example.CrudOperation.repository.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class StudentServiceTest {
    @MockBean
    StudentRepo studentRepo;
    @Autowired
    StudentService studentService;

    @Test
    void createStudentTest() {
        Student student = Student.builder()
                .id(7)
                .firstName("Kimo")
                .lastName("Ashour")
                .job("Singer")
                .build();
        StudentDto studentDto = StudentDto.builder()
                .id(7)
                .firstName("Kimo")
                .lastName("Ashour")
                .job("Singer")
                .build();
        Mockito.when(studentRepo.findById(student.getId())).thenReturn(Optional.of(student));
        Mockito.when(studentRepo.save(student)).thenReturn(student);
         studentService.createStudent(studentDto);
         //Student student1 = studentService.createStudent(studentDto);
        Assertions.assertEquals(36, studentDto.getId());
    }

    @Test
    void getAllStudentsTest() {
        Student student1 = Student.builder()
                .id(1)
                .firstName("ibrahim")
                .lastName("yasin")
                .job("farmer")
                .build();
        Student student2 = Student.builder()
                .id(2)
                .firstName("sara")
                .lastName("yasin")
                .job("doctor")
                .build();
        studentRepo.save(student1);
        studentRepo.save(student2);
        List<Student> students = Arrays.asList(student1, student2);
        Mockito.when(studentRepo.findAll()).thenReturn(students);
        List<StudentDto> studentDto = studentService.getAllStudents(0,5);
        Assertions.assertNotNull(studentDto);
        Assertions.assertEquals(2, studentDto.size());
    }

    @Test
    void getStudentByIdFoundTest() {
        Student student = Student.builder()
                .firstName("ibrahim")
                .lastName("yasin")
                .job("farmer")
                .build();
        Mockito.when(studentRepo.findById(27)).thenReturn(Optional.ofNullable(student));
        StudentDto studentDto1 = studentService.getStudent(27);
        Assertions.assertNotNull(studentDto1);
        Assertions.assertEquals(27, studentDto1.getId());
    }

    @Test
    void updateStudentTest() {
        Student student = Student.builder()
                .id(6)
                .firstName("rahma")
                .lastName("ashraf")
                .job("banker")
                .build();
        StudentDto studentDto = StudentDto.builder()
                .id(6)
                .firstName("rahma")
                .lastName("ashraf")
                .job("banker")
                .build();
        Mockito.when(studentRepo.findById(28)).thenReturn(Optional.ofNullable(student));
        Mockito.when(studentRepo.save(student)).thenReturn(student);
        studentDto.setJob("marker");
        StudentDto stud = studentService.updateStudent(studentDto);
        Assertions.assertNotNull(stud);
        Assertions.assertEquals(28,stud.getId());
    }
    @Test
    void deleteStudentTest() {
        Student student = Student.builder()
                .firstName("Kimo")
                .lastName("Ashour")
                .job("Singer")
                .build();
        Mockito.when(studentRepo.findById(27)).thenReturn(Optional.ofNullable(student));
        studentService.deleteStudent(27);
    }
}