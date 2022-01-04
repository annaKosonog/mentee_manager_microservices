package pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Duration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DurationTypeValidator implements ConstraintValidator<DurationType, Duration> {

    Duration[] subset;
    private static Logger LOG = LoggerFactory.getLogger(DurationTypeValidator.class);


    @Override
    public void initialize(DurationType constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(Duration duration, ConstraintValidatorContext constraintValidatorContext) {
        for (Duration d : subset) {
            if (d.equals(duration)) {
                LOG.info("You entered correct data: ");
                return true;
            }
        }
        LOG.error("You have to choose ONE_MONTH/THREE_MONTH/SIX_MONTH");
        return false;
    }
}

// todo - ciągły błąd z walidacją duration nie wchodzi do piwerwszego if-a tylko od razu rzuca błąd czy wpiszemy dobrą danę czy też źle

