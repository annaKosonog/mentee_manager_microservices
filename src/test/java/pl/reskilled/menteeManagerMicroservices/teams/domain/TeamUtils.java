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

    public static Team allParametersWithTeam(String id, String name, String techStack, Project project, Mentee members) {
        return new Team(id, name,techStack, project, members);
    }

    public static Team parameterWithoutId(String name,String techStack, Project project, Mentee mentee) {
        return new Team(null, name,techStack, project, mentee);
    }

    public static TeamDto allParametersTeamDto(String name, String techStack,ProjectDto projectDto, MenteeDto menteeDto) {
        return new TeamDto(name,techStack, projectDto, menteeDto);
    }

    public static TeamDto masterDtoNull() {
        return allParametersTeamDto("master", "Java",null, null);
    }

    public static TeamDto masterDto() {
        return allParametersTeamDto("master", "Java",pacmanDto(), userPawelDto());
    }

    public static TeamDto vipDto(){
        return allParametersTeamDto("VIP","Java", secretKeyDtoMapper(), userAdamDto());
    }

    public static Team masterDaoNull() {
        return allParametersWithTeam("123AAA", "master", "Java",null, null);
    }

    public static Team masterDao() {
        return allParametersWithTeam("123AAA", "master", "Java",pacmanDao(), userPawelDao());
    }

    public static Team masterWithoutId() {
        return parameterWithoutId("master","Java", pacmanDao(), userPawelDao());
    }
}
