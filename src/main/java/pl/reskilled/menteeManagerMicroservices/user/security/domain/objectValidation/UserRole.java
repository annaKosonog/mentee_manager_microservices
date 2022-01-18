package pl.reskilled.menteeManagerMicroservices.user.security.domain.objectValidation;


import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UserRoleValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRole {

    String message() default "Role: must be any of STUDENT or MENTOR";

    Class<?>[] groups() default {};

    Class<? extends Enum>[] payload() default {};
}
