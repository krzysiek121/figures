package pl.kurs.figures.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckFigureTypeValidator.class)
public @interface CheckFigureType {

    String message() default "INVALID_TYPE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}


