package br.com.gabriella.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OptionalNotBlankValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionalNotBlank {

    String message() default "campo não pode estar em branco";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
