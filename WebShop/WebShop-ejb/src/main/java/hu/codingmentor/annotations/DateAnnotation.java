package hu.codingmentor.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = DateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface DateAnnotation {

    String message() default "The registration date can not be earlier then the date of birth";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
