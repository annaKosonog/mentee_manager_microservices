package pl.reskilled.menteeManagerMicroservices.user.security.domain.objectValidation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

@Slf4j
public class UserRoleValidator implements ConstraintValidator<UserRole, Set<String>> {


    @Override
    public void initialize(UserRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(Set<String> roles, ConstraintValidatorContext constraintValidatorContext) {
        if (roles.contains("STUDENT") || roles.contains("MENTOR")) {
            log.info("Correct role: ");
            return true;
        } else {
            log.error(" \b The user has entered his STUDENT/MENTOR role incorrectly ");
        }
        return false;
    }
}
