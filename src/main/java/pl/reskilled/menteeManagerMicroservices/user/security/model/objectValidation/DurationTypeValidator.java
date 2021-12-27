package pl.reskilled.menteeManagerMicroservices.user.security.model.objectValidation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.reskilled.menteeManagerMicroservices.user.security.model.student.Duration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;


public class DurationTypeValidator implements ConstraintValidator<DurationType, Duration> {

    private static Logger LOG = LoggerFactory.getLogger(DurationTypeValidator.class);


    @Override
    public void initialize(DurationType constraintAnnotation) {}

    @Override
    public boolean isValid(Duration duration, ConstraintValidatorContext constraintValidatorContext) {
        List<Duration> listDuration = Arrays.asList(Duration.ONE_MONTH, Duration.THREE_MONTH, Duration.SIX_MONTH);
              if(listDuration.contains(duration)){
                LOG.info("You entered correct data: ");
                return true;
        }
        LOG.error("You have to choose ONE_MONTH/THREE_MONTH/SIX_MONTH");
        return false;
    }
}

// todo - ciągły błąd z walidacją duration nie wchodzi do piwerwszego if-a tylko od razu rzuca błąd czy wpiszemy dobrą danę czy też źle

