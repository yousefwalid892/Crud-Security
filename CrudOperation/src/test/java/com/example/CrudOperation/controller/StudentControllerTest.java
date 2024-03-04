package com.example.CrudOperation.controller;

import com.example.CrudOperation.model.dto.StudentDto;
import com.example.CrudOperation.model.entity.Student;
import com.example.CrudOperation.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
       @Autowired
       MockMvc mockMvc;
       @MockBean
       StudentService studentService;
       @Autowired
       ObjectMapper objectMapper;

       @Test
       void createStudentTest() throws Exception {
              Student student = Student.builder()
                      .id(30)
                      .firstName("Kimo")
                      .lastName("Ashour")
                      .job("Singer")
                      .build();
              StudentDto studentDto = StudentDto.builder()
                      .id(30)
                      .firstName("Kimo")
                      .lastName("Ashour")
                      .job("Singer")
                      .build();
              Mockito.when(studentService.createStudent(studentDto)).thenReturn(student);
              mockMvc.perform(post("/students/add")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(studentDto)))
                      .andExpect(status().isCreated());
       }

       @Test
       void getAllStudentsTest() throws Exception {
              StudentDto student1 = StudentDto.builder()
                      .id(44)
                      .firstName("kimo")
                      .lastName("ashour")
                      .job("singer")
                      .build();
              StudentDto student2 = StudentDto.builder()
                      .id(45)
                      .firstName("Kimo")
                      .lastName("Ashour")
                      .job("Singer")
                      .build();
              List<StudentDto> students = Arrays.asList(student1, student2);
              Mockito.when(studentService.getAllStudents(0,5)).thenReturn(students);
              mockMvc.perform(get("/students")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(students)));
                  //    .andExpect(MockMvcResultMatchers.status().isOk()));
       }
       @Test
       void getStudentTest() throws Exception {
              StudentDto studentDto = StudentDto.builder()
                      .id(27)
                      .firstName("Kimo")
                      .lastName("Ashour")
                      .job("Singer")
                      .build();
              Mockito.when(studentService.getStudent(27)).thenReturn(studentDto);
              mockMvc.perform(get("/students/{id}",27)
                              .contentType(MediaType.APPLICATION_JSON)
                              .content(objectMapper.writeValueAsString(studentDto)));
                        //      .andExpect(MockMvcResultMatchers.status().isOk()));
       }
       @Test
       void updateStudentTest() throws Exception {
              StudentDto studentDto = StudentDto.builder()
                      .id(27)
                      .firstName("Kimo")
                      .lastName("Ashour")
                      .job("Singer")
                      .build();
              Mockito.when(studentService.updateStudent(studentDto)).thenReturn(studentDto);
              mockMvc.perform(put("/students/update")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(studentDto)));
                   //   .andExpect(MockMvcResultMatchers.status().isOk()));
       }
       @Test
       void deleteStudentTest() throws Exception {
              StudentDto studentDto = StudentDto.builder()
                      .id(27)
                      .firstName("Kimo")
                      .lastName("Ashour")
                      .job("Singer")
                      .build();
              doNothing().when(studentService).deleteStudent(27);
              mockMvc.perform(delete("/students/delete/{id}",27)
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(studentDto)));
                     // .andExpect(MockMvcResultMatchers.status().isOk()));
       }
}
