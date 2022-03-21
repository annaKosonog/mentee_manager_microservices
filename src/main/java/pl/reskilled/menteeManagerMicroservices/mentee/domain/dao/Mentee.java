package pl.reskilled.menteeManagerMicroservices.mentee.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation.DurationType;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.objectValidation.SeniorityType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@ToString
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
    private String futurePosition;

    @NotBlank
    @DurationType
    private Duration duration;

    @NotBlank
    @SeniorityType
    private Seniority seniority;
}
