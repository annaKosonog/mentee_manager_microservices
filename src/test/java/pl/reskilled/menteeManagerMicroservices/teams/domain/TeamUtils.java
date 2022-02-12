package pl.reskilled.menteeManagerMicroservices.teams.domain;

import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;
import pl.reskilled.menteeManagerMicroservices.project.domain.Project;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;

import static pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeUtil.userAdamDto;
import static pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeUtil.userPawelDao;
import static pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeUtil.userPawelDto;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.pacmanDao;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.pacmanDto;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.secretKeyDtoMapper;

public class TeamUtils {

    public static Team allParametersWithTeam(String id, String name, Project project, Mentee members) {
        return new Team(id, name, project, members);
    }

    public static Team parameterWithoutId(String name, Project project, Mentee mentee) {
        return new Team(null, name, project, mentee);
    }

    public static TeamDto allParametersTeamDto(String name, ProjectDto projectDto, MenteeDto menteeDto) {
        return new TeamDto(name, projectDto, menteeDto);
    }

    public static TeamDto masterDtoNull() {
        return allParametersTeamDto("master", null, null);
    }

    public static TeamDto masterDto() {
        return allParametersTeamDto("master", pacmanDto(), userPawelDto());
    }

    public static TeamDto vipDto(){
        return allParametersTeamDto("VIP", secretKeyDtoMapper(), userAdamDto());
    }

    public static Team masterDaoNull() {
        return allParametersWithTeam("123AAA", "master", null, null);
    }

    public static Team masterDao() {
        return allParametersWithTeam("123AAA", "master", pacmanDao(), userPawelDao());
    }

    public static Team masterWithoutId() {
        return parameterWithoutId("master", pacmanDao(), userPawelDao());
    }
}
