package pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation;


import lombok.extern.slf4j.Slf4j;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Duration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class DurationTypeValidator implements ConstraintValidator<DurationType, Duration> {


    private Set<Duration> subset;


    @Override
    public void initialize(DurationType constraintAnnotation) {
        subset = Arrays.stream(constraintAnnotation.anyOf()).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Duration duration, ConstraintValidatorContext constraintValidatorContext) {
        if (duration.equals("ONE_MONTH") || duration.equals("THREE_MONTH") || duration.equals("SIX_MONTH")) {
            log.info("You entered correct data: ");
            return true;
        }
        return true;
        }
    }



// todo - ciągły błąd z walidacją duration nie wchodzi do piwerwszego if-a tylko od razu rzuca błąd czy wpiszemy dobrą danę czy też źle

