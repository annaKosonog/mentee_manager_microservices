package pl.reskilled.menteeManagerMicroservices.user.security.model.objectValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.Authority;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;


public class RoleUserValidator implements ConstraintValidator<UserRoleValid, Set<Authority>> {

    private static Logger LOG = LoggerFactory.getLogger(RoleUserValidator.class);

    Authority[] subset;

    @Override
    public void initialize(UserRoleValid constraintAnnotation) {
        subset = constraintAnnotation.anyOf();
    }


    @Override
    public boolean isValid(Set<Authority> authority, ConstraintValidatorContext constraintValidatorContext) {
        if (authority.contains(Authority.STUDENT) || authority.contains(Authority.MENTOR)) {
            LOG.info("Correct role: ");
            return true;
        } else {
            LOG.error(" \b The user has entered his STUDENT/MENTOR role incorrectly ");
        }
            return false;
    }
}
