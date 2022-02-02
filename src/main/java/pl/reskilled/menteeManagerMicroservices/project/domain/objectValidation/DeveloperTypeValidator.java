package pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation;

import com.amazonaws.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.regex.Pattern;


public class DeveloperTypeValidator implements ConstraintValidator<DeveloperType, Set<String>> {

    final private Pattern developersPattern = Pattern.compile("^[\\p{Lu}]([-']?[\\p{Ll}]+)*( [\\p{Lu}]([-']?[\\p{Ll}]+)*)+$");

    @Override
    public boolean isValid(final Set<String> developer, ConstraintValidatorContext constraintValidatorContext) {
        return developer.stream()
                .filter(e -> !StringUtils.isNullOrEmpty(e)).
                        filter(e -> developersPattern.matcher(e).matches()).count() == developer.size();
    }
}
