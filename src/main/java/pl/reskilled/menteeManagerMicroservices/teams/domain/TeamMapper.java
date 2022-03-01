package pl.reskilled.menteeManagerMicroservices.teams.domain;

import lombok.experimental.UtilityClass;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeMapper;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectMapper;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamReadDto;

@UtilityClass
public class TeamMapper {

    public TeamDto mapToTeamDto(Team team) {
        return TeamDto.builder()
                .name(team.getName())
                .techStack(team.getTechStack())
                .project(ProjectMapper.mapToProjectDto(team.getProject()))
                .members(MenteeMapper.mapToMenteeDto(team.getMentees()))
                .build();
    }


    public TeamReadDto mapToTeamReadDto(Team team) {
        return TeamReadDto.builder()
                .name(team.getName())
                .techStack(team.getTechStack())
                .projects(ProjectMapper.mapToProjectDto(team.getProject()))
                .members(MenteeMapper.mapToMenteeDto(team.getMentees()))
                .build();
    }

    public Team mapToTeam(TeamDto teamDto) {

        final Team team = new Team();
        team.setName(teamDto.getName());
        team.setTechStack(teamDto.getTechStack());
        team.setProject(ProjectMapper.mapToProject(teamDto.getProject()));
        team.setMentees(MenteeMapper.mapToMentee(teamDto.getMembers()));
        return team;
    }
}
