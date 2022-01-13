package pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Duration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

@Slf4j
@Getter
public class DurationTypeValidator implements ConstraintValidator<DurationType, Duration> {


    private Set<Duration> subset;


    @Override
    public void initialize(DurationType constraintAnnotation) {
    }

    @Override
    public boolean isValid(Duration duration, ConstraintValidatorContext constraintValidatorContext) {
        if(duration ==null){
            return false;
        }
        if (duration == Duration.ONE_MONTH || duration == Duration.THREE_MONTH || duration == Duration.SIX_MONTH) {
            log.info("You entered correct data: ");
            return true;
        }
        return false;
        }
    }

