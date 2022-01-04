package pl.reskilled.menteeManagerMicroservices.mentee.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
@JsonDeserialize(builder = MenteeDto.MenteeDtoBuilder.class)

public class MenteeDto implements Serializable {

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
