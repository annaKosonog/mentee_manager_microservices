package pl.reskilled.menteeManagerMicroservices.teams.domain;

import lombok.experimental.UtilityClass;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeMapper;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectMapper;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamReadDto;

@UtilityClass
public class TeamMapper {

    public static TeamDto mapToTeamDto(Team team) {
        return TeamDto.builder()
                .name(team.getName())
                .project(ProjectMapper.mapToProjectDto(team.getProject()))
                .members(MenteeMapper.mapToMenteeDto(team.getMentee()))
                .build();
    }


    public static TeamReadDto mapToTeamReadDto(Team team) {
        return TeamReadDto.builder()
                .name(team.getName())
                .project(ProjectMapper.mapToProjectDto(team.getProject()))
                .members(MenteeMapper.mapToMenteeDto(team.getMentee()))
                .build();
    }

    public static Team reverseToTeam(TeamDto teamDto) {

        final Team team = new Team();
        team.setName(teamDto.getName());
        team.setProject(ProjectMapper.mapToProject(teamDto.getProject()));
        team.setMentee(MenteeMapper.mapToMentee(teamDto.getMembers()));
        return team;
    }
}