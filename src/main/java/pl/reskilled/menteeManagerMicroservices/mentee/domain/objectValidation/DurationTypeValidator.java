package pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation;


import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class DurationTypeValidator implements ConstraintValidator<DurationType, String> {


    @Override
    public void initialize(DurationType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String duration, ConstraintValidatorContext constraintValidatorContext) {
        if (duration.contains("ONE_MONTH") || duration.contains("THREE_MONTH") || duration.contains("SIX_MONTH")) {
            log.info("You entered correct data: ");
            return true;
        }
        log.error("\b Incorrect data was entered, any of the given ONE_MONTH / THREE_MONTH / SIX_MONTH should occur");
        return false;
    }
}

