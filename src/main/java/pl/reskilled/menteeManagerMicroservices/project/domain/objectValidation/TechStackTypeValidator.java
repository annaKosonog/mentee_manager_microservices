package pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class TechStackTypeValidator implements ConstraintValidator<TechStackType, Set<String>> {
    @Override
    public void initialize(TechStackType constraintAnnotation) {
    }

    @Override
    public boolean isValid(Set<String> techStack, ConstraintValidatorContext constraintValidatorContext) {
        return techStack.isEmpty();
    }
}

