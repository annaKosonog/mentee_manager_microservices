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
    private Duration duration;
    @NotBlank
    private Seniority seniority;
}
