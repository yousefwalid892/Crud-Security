package com.example.CrudOperation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Constraint(validatedBy = AgeValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {
    String message() default "{Age is invalid}";        //error message

    Class<?>[] groups() default {};     //represent group of constraints.

    Class<? extends Payload>[] payload() default {};    // additional info about annotation
}
