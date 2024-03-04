package com.example.CrudOperation.repository;

import com.example.CrudOperation.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
