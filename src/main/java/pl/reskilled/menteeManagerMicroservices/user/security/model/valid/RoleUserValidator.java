package pl.reskilled.menteeManagerMicroservices.user.security.model.valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.reskilled.menteeManagerMicroservices.user.security.model.Authority;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;


public class RoleUserValidator implements ConstraintValidator<UserRoleValid, Set<Authority>> {

    private static Logger LOG = LoggerFactory.getLogger(RoleUserValidator.class);

    String[] values;

    @Override
    public void initialize(UserRoleValid constraintAnnotation) {
        values = constraintAnnotation.allowedValues();
    }


    @Override
    public boolean isValid(Set<Authority> authority, ConstraintValidatorContext constraintValidatorContext) {
        String message = "Choose STUDENT or MENTOR";
        if (authority.contains(Authority.STUDENT) || authority.contains(Authority.MENTOR)) {
            LOG.info("Correct role: ");
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message);
            LOG.error(" \b The user has entered his STUDENT/MENTOR role incorrectly ");
            return false;

        }
    }
}
