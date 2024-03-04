package com.example.CrudOperation.service;

import com.example.CrudOperation.exception.StudentAlreadyExistsException;
import com.example.CrudOperation.exception.StudentNotFoundException;
import com.example.CrudOperation.mapper.StudentMapper;
import com.example.CrudOperation.model.dto.StudentDto;
import com.example.CrudOperation.model.entity.Student;
import com.example.CrudOperation.repository.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepo studentRepo;
    @Autowired
    private StudentMapper studentMapper;

    Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    public StudentServiceImpl(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
   @Cacheable(value = "Students" ,key = "#studentDto.id")
    @Override
    public Student createStudent(StudentDto studentDto) {
        Optional<Student> stu = studentRepo.findById(studentDto.getId());
        if (stu.isEmpty()) {
            log.info("This is log",studentDto.getId(),studentDto.getFirstName());
            return studentRepo.save(studentMapper.toEntity(studentDto));
        } else {
            log.error("Student is existing");
            throw new StudentAlreadyExistsException("Student exists");
        }
    }
    @Cacheable(value = "Students")
    @Query(value = "SELECT students.age FROM students ORDER BY id ")
    @Override
    public List<StudentDto> getAllStudents(int pageNo ,int pageSize) {
           Pageable pageable = PageRequest.of(pageNo,pageSize);
           Page<Student> studentPage = studentRepo.findAll(pageable);  //findAll:Takes pageable object & return page of records.
           List<Student> studentList= studentPage.getContent();
           return studentList.stream().map(s -> studentMapper.toDto(s)).collect(Collectors.toList());
        //   List<StudentDto> studentDtoList = studentMapper.toDto(studentPage);
        //   return  studentDtoList;
    }

    @Cacheable(value = "Students", key = "#id")
    @Override
    public StudentDto getStudent(int id) {
        Optional<Student> stu = studentRepo.findById(id);
        if(stu.isPresent()){
            return studentMapper.toDto(stu.get());
        }
        else{
            log.error("Student not exist");
            throw new StudentNotFoundException("Student not exist with id:"+ id);
        }
    }
    @CachePut(value = "Students", key = "#studentDto")
    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student existingstudent = studentRepo.findById(studentDto.getId()).get();
        existingstudent.setFirstName(studentDto.getFirstName());
        existingstudent.setLastName(studentDto.getLastName());
        existingstudent.setId(studentDto.getId());
        existingstudent.setJob(studentDto.getJob());
        existingstudent.setAge(studentDto.getAge());
        existingstudent.setEmail(studentDto.getEmail());
        Student updatedstudent = studentRepo.save(existingstudent);
        return studentMapper.toDto(updatedstudent);
         //  Student student = studentRepo.findById(studentDto.getId()).get();    //return id of student(dto) from DB.
         // Student mapped = studentMapper.toUpdate(studentDto,student);      //put the updated properties of dto into entity.
         // return studentMapper.toDto(studentRepo.save(mapped));             //then convert to dto and return dto.
    }
    @CacheEvict(value = "Students", key = "#id")
    @Override
    public void deleteStudent(int id) {
           studentRepo.deleteById(id);
    }
}


