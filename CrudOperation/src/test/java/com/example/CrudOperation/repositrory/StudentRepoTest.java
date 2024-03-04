package com.example.CrudOperation.repositrory;

import com.example.CrudOperation.model.entity.Student;
import com.example.CrudOperation.repository.StudentRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class StudentRepoTest {
    @Autowired
    StudentRepo studentRepo;
    @Test
    void createStudentTest(){
         Student student = Student.builder()
                 .id(7)
                 .firstName("kimo")
                 .lastName("ashour")
                 .job("Singer")
                 .age(25)
                 .build();
        Student student1 = studentRepo.save(student);
        Assertions.assertThat(student1).isNotNull();
    }
    @Test
    void getAllStudentsTest(){
        List<Student> students = studentRepo.findAll();
        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students.size()).isEqualTo(7);
    }

    @Test
    void getStudentByIdFoundTest(){
        Student student1 = Student.builder().id(1).firstName("ibrahim").lastName("yasin").job("farmer").build();
        studentRepo.save(student1);
        Student stuReturn = studentRepo.findById(student1.getId()).get();
        Assertions.assertThat(stuReturn).isNotNull();
    }
    @Test
    void getStudentByIdNotFoundTest(){
        Student stu = studentRepo.findById(33).get();
        Assertions.assertThat(stu).isNull();
    }
    /*
    @Test
    void updateStudentTest(){
        Student updatedstudent = studentRepo.findById(21).get();
        updatedstudent.setFirstName("walid");
        updatedstudent.setLastName("tamer");
        updatedstudent.setJob("HR");
        studentRepo.save(updatedstudent);
        Assertions.assertThat(updatedstudent.getFirstName()).isEqualTo("walid");
        Assertions.assertEquals(30,updatedstudent.getid());
    }
     */
    @Test
    void updateStudentTest(){
        Student student1 = Student.builder().id(1).firstName("ibrahim").lastName("yasin").job("farmer").build();
        studentRepo.save(student1);
        Student stuReturn = studentRepo.findById(student1.getId()).get();
        stuReturn.setId(1);
        stuReturn.setFirstName("ramzy");
        stuReturn.setLastName("mohsen");
        stuReturn.setJob("policeman");
        Student updatedStudent = studentRepo.save(stuReturn);
        Assertions.assertThat(updatedStudent).isNotNull();
    }

    @Test
    void deleteStudentTest(){
        Student student1 = Student.builder().id(22).firstName("wael").lastName("marcos").job("tester").build();
        studentRepo.save(student1);
        studentRepo.deleteById(student1.getId());
        Optional<Student> studentOptional = studentRepo.findById(student1.getId());
        Assertions.assertThat(studentOptional).isEmpty();
    }
}
