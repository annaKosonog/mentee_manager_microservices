package pl.reskilled.menteeManagerMicroservices.user.security.model.objectValidation;

import pl.reskilled.menteeManagerMicroservices.user.security.model.student.Duration;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DurationTypeValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DurationType {

    Duration[] anyOf();

    String message() default  "Must be any of {anyOf}";


    Class<?>[] groups() default {};

    Class<? extends Enum>[] payload() default {};

}
