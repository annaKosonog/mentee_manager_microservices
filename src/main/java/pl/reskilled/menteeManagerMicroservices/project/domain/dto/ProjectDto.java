package pl.reskilled.menteeManagerMicroservices.project.domain.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    private final Set<@NotEmpty(message = "{developers.not.empty}") String> developers;

    private final Set<@NotEmpty(message = "{techStack.not.empty}") String> techStack;

    @NotBlank(message = "{description.not.blank}")
    private final String description;
}
