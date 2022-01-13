package pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation;

import lombok.extern.slf4j.Slf4j;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Seniority;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class SeniorityTypeValidator implements ConstraintValidator<SeniorityType, Seniority> {


    @Override
    public void initialize(SeniorityType constraintAnnotation) {
    }

    @Override
    public boolean isValid(Seniority seniority, ConstraintValidatorContext constraintValidatorContext) {
        if(seniority == null){
            return false;
        }
        if (seniority ==Seniority.INTERN || seniority ==Seniority.JUNIOR || seniority ==Seniority.MID || seniority ==Seniority.SENIOR) {
            log.info("You entered correct data: ");
            return true;
        }
        return false;
    }
}
// todo - ciągły błąd z walidacją seniority
