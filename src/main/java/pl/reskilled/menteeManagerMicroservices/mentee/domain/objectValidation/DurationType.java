package pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation;

import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Duration;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = DurationTypeValidator.class)
@Target({FIELD, PARAMETER, METHOD, ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RUNTIME)
public @interface DurationType {

    Duration[] anyOf() default {Duration.ONE_MONTH};

    String message() default  "Must be any of {anyOf}";


    Class<?>[] groups() default {};

    Class<? extends Enum>[] payload() default {};

}
