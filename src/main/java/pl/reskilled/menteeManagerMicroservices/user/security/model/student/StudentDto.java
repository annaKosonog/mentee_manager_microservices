package pl.reskilled.menteeManagerMicroservices.user.security.model.student;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.reskilled.menteeManagerMicroservices.user.security.model.objectValidation.DurationType;
import pl.reskilled.menteeManagerMicroservices.user.security.model.objectValidation.SeniorityType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
@ToString
@JsonDeserialize(builder = StudentDto.StudentDtoBuilder.class)
public class StudentDto implements Serializable {

    private static final long serialVersionUID = -4856846361193249489L;

    @NotBlank(message = "{username.not.blank}")
    private final String username;
    @Email
    @NotBlank(message = "{email.not.blank}")
    private final String email;
    @NotBlank(message = "{future_position.not.blank}")
    private final String future_position;
    @DurationType(anyOf = {Duration.ONE_MONTH, Duration.THREE_MONTH, Duration.SIX_MONTH})
    private final Duration duration;
    @SeniorityType(anyOf = {Seniority.Intern, Seniority.Junior, Seniority.Mid, Seniority.Senior})
    private final Seniority seniority;

}
