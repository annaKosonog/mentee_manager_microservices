package pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DeveloperTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeveloperType{
    String message() default "Developer may not be blank";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
