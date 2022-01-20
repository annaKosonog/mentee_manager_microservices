package pl.reskilled.menteeManagerMicroservices.project.domain.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation.DeveloperType;
import pl.reskilled.menteeManagerMicroservices.project.domain.objectValidation.TechStackType;

import javax.validation.constraints.NotBlank;
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

    @TechStackType
    private final Set<String> techStack;

    @NotBlank(message = "{description.not.blank}")
    private final String description;
}
