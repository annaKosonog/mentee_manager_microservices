package pl.reskilled.menteeManagerMicroservices.teams.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class TeamDto implements Serializable {
    private static final long serialVersionUID = -4856846361193249489L;

    @NotBlank(message = "{name.not.blank}")
    private  String name;


    private   ProjectDto project;


    private MenteeDto members;

}
