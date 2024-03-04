package com.example.CrudOperation.model.dto;

import com.example.CrudOperation.validator.Age;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class StudentDto implements Serializable{   //dto:class for client    //can change attribute names or add or remove.

    @NotNull(message = "Enter the id")
    private int id;
    @NotBlank(message = "You should enter the firstName")
  //  @Pattern(regexp = "^([a-z])$")
    private String firstName;
    @NotBlank(message = "You should enter the secondName")
    private String lastName;
    @NotNull(message = "You should enter the job")
    private String job;
    @NotNull
    @Age
    private int age;
    @Column
    @NotNull(message = "You should enter the email")
    @Email
    private String email;

    // @NotNull(message = "You should enter the gender")
    //public enum gender {male,female}
}

