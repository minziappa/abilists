package com.abilists.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmptyMaxSizeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmptyMaxSize {

    String message() default "{EmptyMaxSize}";
    
    // String length
    int max() default 1;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}