package pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class DeveloperTypeValidator implements ConstraintValidator<DeveloperType, Set<String>> {

    @Override
    public void initialize(DeveloperType constraintAnnotation) {

    }

    @Override
    public boolean isValid(Set<String> developer, ConstraintValidatorContext constraintValidatorContext) {
        return developer.isEmpty();
    }
}
