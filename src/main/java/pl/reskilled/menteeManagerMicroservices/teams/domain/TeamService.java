package pl.reskilled.menteeManagerMicroservices.teams.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeService;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamReadDto;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final MenteeService menteeService;
    private final ProjectService projectService;


    public TeamDto createNewTeam(TeamDto teamDto, String name, String email) {
        teamDto.setProject(projectService.findProjectByName(name));
        teamDto.setMembers(menteeService.findMenteeByEmail(email));
        final Team team = TeamMapper.mapToTeam(teamDto);
        teamRepository.save(team);
        log.info("THE TASK COMPLETED SUCCESSFULLY: ");
        return TeamMapper.mapToTeamDto(team);
    }

    public List<TeamReadDto> findAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(TeamMapper::mapToTeamReadDto)
                .collect(Collectors.toList());
    }
}
