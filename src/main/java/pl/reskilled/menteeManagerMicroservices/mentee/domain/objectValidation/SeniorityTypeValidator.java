package pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation;

import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Seniority;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class SeniorityTypeValidator implements ConstraintValidator<SeniorityType, Seniority> {

    List<String> subset = Arrays.asList("Intern", "Junior", "Mid", "Senior");


    @Override
    public void initialize(SeniorityType constraintAnnotation) {
    }

    @Override
    public boolean isValid(Seniority seniority, ConstraintValidatorContext constraintValidatorContext) {
        return subset.contains(seniority);
    }
}
// todo - ciągły błąd z walidacją seniority
