package pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class SeniorityTypeValidator implements ConstraintValidator<SeniorityType, String> {

    @Override
    public void initialize(SeniorityType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String seniority, ConstraintValidatorContext constraintValidatorContext) {
        if (("INTERN").equals(seniority) || ("JUNIOR").equals(seniority) || ("MID").equals(seniority) || ("SENIOR").equals(seniority)) {
            log.info("You entered correct data: ");
            return true;
        }
        log.error("\b Incorrect data was entered, any of the given INTERN / JUNIOR / MID / SENIOR should occur");
        return false;
    }
}

