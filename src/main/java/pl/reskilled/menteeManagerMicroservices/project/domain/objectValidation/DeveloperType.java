package pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = DeveloperTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface DeveloperType{
    String message() default "Developer data incorrectly entered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
