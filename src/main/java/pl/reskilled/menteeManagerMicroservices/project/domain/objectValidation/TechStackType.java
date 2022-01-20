package pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TechStackTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TechStackType {
    String message() default "Tech stack: may not be blank";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
