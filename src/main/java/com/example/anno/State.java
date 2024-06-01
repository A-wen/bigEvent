package com.example.anno;


import com.example.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(
        validatedBy = {StateValidation.class}//指定提供較驗class
)
public @interface State {
    String message() default "state參數值只能是發佈或是草稿";
    //指定分組
    Class<?>[] groups() default {};
    //
    Class<? extends Payload>[] payload() default {};
}
