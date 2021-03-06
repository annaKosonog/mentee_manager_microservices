package pl.reskilled.menteeManagerMicroservices.project.domain.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation.DeveloperType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@EqualsAndHashCode
@Builder
@RequiredArgsConstructor
public class ProjectDto implements Serializable {

    private static final long serialVersionUID = -4856846361193249489L;

    @NotBlank(message = "{name.not.blank}")
    private final String name;

    @DeveloperType
    private final Set<String> developers;

    @NotEmpty(message = "{techStack.not.empty}")
    @Size(min = 2, max = 10, message = "{techStack.size}")
    private final Set<String> techStack;

    @NotBlank(message = "{description.not.blank}")
    private final String description;

    @Override
    public String toString() {
        return  ", name: " + name + "\n" +
                ", developers: " + developers + "\n" +
                ", tech_stack: " + techStack + "\n" +
                ", description: " + description;
    }
}
