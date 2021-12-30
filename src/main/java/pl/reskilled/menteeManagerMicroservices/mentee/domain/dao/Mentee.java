package pl.reskilled.menteeManagerMicroservices.mentee.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pl.reskilled.menteeManagerMicroservices.user.security.model.objectValidation.DurationType;
import pl.reskilled.menteeManagerMicroservices.user.security.model.objectValidation.SeniorityType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@Document(collection = "mentees")
public class Mentee {

    @Id
    private String id;
    @NotBlank
    private String username;

    @Email
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;

    @NotBlank
    @Field(name = "future_position")
    private String future_position;
    @NotBlank
    @DurationType(anyOf = {Duration.ONE_MONTH, Duration.THREE_MONTH, Duration.SIX_MONTH})
    private Duration duration;
    @NotBlank
    @SeniorityType(anyOf = {Seniority.Intern, Seniority.Junior, Seniority.Mid, Seniority.Senior})
    private Seniority seniority;
}
