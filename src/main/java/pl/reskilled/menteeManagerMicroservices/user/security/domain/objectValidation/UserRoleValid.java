package pl.reskilled.menteeManagerMicroservices.user.security.domain.objectValidation;


import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.Authority;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = RoleUserValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserRoleValid {

    Authority [] anyOf();

    String message() default "Must be any of {anyOf}";

    Class<?>[] groups() default {};

    Class<? extends Enum>[] payload() default {};
}
