package pl.reskilled.menteeManagerMicroservices.user.security.domain.objectValidation;

import lombok.extern.slf4j.Slf4j;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.Authority;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

@Slf4j
public class RoleUserValidator implements ConstraintValidator<UserRoleValid, Set<Authority>> {

    Authority [] subset;


    @Override
    public void initialize(UserRoleValid constraintAnnotation) {
        subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(Set<Authority> roles, ConstraintValidatorContext constraintValidatorContext) {
        if (roles.contains(Authority.STUDENT) || roles.contains(Authority.MENTOR)) {
            log.info("Correct role: ");
            return true;
        } else {
            log.error(" \b The user has entered his STUDENT/MENTOR role incorrectly ");
        }
        return false;
    }
}
