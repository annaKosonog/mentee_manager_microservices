package pl.reskilled.menteeManagerMicroservices.teams.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeMapper;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectMapper;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamReadDto;

@Component
@RequiredArgsConstructor
public class TeamMapper {

    private final ProjectMapper projectMapper;
    private final MenteeMapper menteeMapper;

    public TeamDto mapToTeamDto(Team team) {
        return TeamDto.builder()
                .name(team.getName())
                .techStack(team.getTechStack())
                .project(projectMapper.mapToProjectDto(team.getProject()))
                .members(menteeMapper.mapToMenteeDto(team.getMentees()))
                .build();
    }


    public TeamReadDto mapToTeamReadDto(Team team) {
        return TeamReadDto.builder()
                .name(team.getName())
                .techStack(team.getTechStack())
                .projects(projectMapper.mapToProjectDto(team.getProject()))
                .members(menteeMapper.mapToMenteeDto(team.getMentees()))
                .build();
    }

    public Team mapToTeam(TeamDto teamDto) {

        final Team team = new Team();
        team.setName(teamDto.getName());
        team.setTechStack(teamDto.getTechStack());
        team.setProject(projectMapper.mapToProject(teamDto.getProject()));
        team.setMentees(menteeMapper.mapToMentee(teamDto.getMembers()));
        return team;
    }
}
