package pl.reskilled.menteeManagerMicroservices.project.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation.DeveloperType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "projects")
public class Project {

    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @NotBlank
    private String name;

    @DeveloperType
    private Set<String> developers = new HashSet<>();

    @NotEmpty
    @Size
    @Field(name = "tech_stack")
    private Set<String> techStack = new HashSet<>();

    @NotBlank
    private String description;
}
