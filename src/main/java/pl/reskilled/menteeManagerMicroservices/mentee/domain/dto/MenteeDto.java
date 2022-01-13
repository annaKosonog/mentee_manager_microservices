package pl.reskilled.menteeManagerMicroservices.mentee.domain.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Duration;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Seniority;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation.DurationType;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation.SeniorityType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@Builder
@ToString

public class MenteeDto implements Serializable {

    private static final long serialVersionUID = -4856846361193249489L;

    @NotBlank(message = "{username.not.blank}")
    private final String username;
    @Email
    @NotBlank(message = "{email.not.blank}")
    private final String email;
    @NotBlank(message = "{future_position.not.blank}")
    private final String future_position;
    @DurationType
    private final Duration duration;
    @SeniorityType
    private final Seniority seniority;

}
