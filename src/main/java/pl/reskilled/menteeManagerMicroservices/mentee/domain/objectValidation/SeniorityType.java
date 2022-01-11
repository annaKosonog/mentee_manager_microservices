package pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = SeniorityTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SeniorityType {

    String message() default "Must be any of Intern/Junior/Mid/Senior";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
