package pl.reskilled.menteeManagerMicroservices.teams.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;
import pl.reskilled.menteeManagerMicroservices.project.domain.Project;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Document(collection = "teams")
public class Team {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    @Field(name = "tech_stack")
    private String techStack;

    @JsonIgnore
    private Project project;

    @JsonIgnore
    private Mentee mentees;
}
