package com.example.CrudOperation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgeValidator implements ConstraintValidator<Age, Integer> {        //<Annotation,data type of field>

    enum gender{male,female}

    private gender Gender;
    Logger logger = LoggerFactory.getLogger(AgeValidator.class);

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext constraintValidatorContext) {
           if((Gender == gender.male) && (age < 35)){
               return false;
           } else if ((Gender == gender.female) && (age < 40)) {
                return false;
           }
        return true;
    }
}