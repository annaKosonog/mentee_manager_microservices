package pl.reskilled.menteeManagerMicroservices.teams.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Builder
@Getter
public class TeamReadDto {
    @NotBlank(message = "{name.not.blank}")
    private final String name;


    private final ProjectDto project;


    private final MenteeDto members;
}
