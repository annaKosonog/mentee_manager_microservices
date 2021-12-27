package pl.reskilled.menteeManagerMicroservices.user.security.model.objectValidation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.reskilled.menteeManagerMicroservices.user.security.model.student.Seniority;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class SeniorityTypeValidator implements ConstraintValidator<SeniorityType, Seniority> {

    private static Logger LOG = LoggerFactory.getLogger(SeniorityTypeValidator.class);
    Seniority[] subset;


    @Override
    public void initialize(SeniorityType constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(Seniority seniority, ConstraintValidatorContext constraintValidatorContext) {
        List<String> listSeniority = Arrays.asList("Intern", "Junior", "Mid", "Senior");
        if (listSeniority.contains(seniority)) {
                LOG.info("You entered correct data: ");
                return true;
            }
        LOG.error("You have to choose Intern/Junior/Mid/Senior");
        return false;
    }
}
// todo - ciągły błąd z walidacją seniority
