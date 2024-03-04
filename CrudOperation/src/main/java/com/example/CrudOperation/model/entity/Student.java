package com.example.CrudOperation.model.entity;

import com.example.CrudOperation.validator.Age;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "Students")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)            //IDENTITY= Auto-increment
    @NotNull(message = "Enter the id")
    private int id;
    @Column(name = "firstName")
    @NotBlank(message = "Name should not be null or empty")
   // @Pattern(regexp = "^([a-z])$")
    private String firstName;
    @Column(name = "lastName")
    @NotBlank(message = "Name should not be null or empty")
    private String lastName;
    @Column(name = "job")
    @NotNull(message = "You should enter the job")
    private String job;
    @Column(name = "age")
    @NotNull
    @Age
    private int age;
    @Column
    @NotNull(message = "You should enter the email")
    @Email
    private String email;
}
