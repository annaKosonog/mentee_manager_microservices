package pl.reskilled.menteeManagerMicroservices.teams.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeService;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;

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
        log.info("----------------------------");
        log.info("START ADDING TEAM TO DB: ");
        teamDto.setProject(projectService.findProjectByName(name));
        teamDto.setMembers(menteeService.findMenteeByEmail(email));
        log.info("BEFORE SAVE TEAM TO DB:");
        final Team team = TeamMapper.reverseToTeam(teamDto);
        teamRepository.save(team);
        log.info("-------------------------------------------------");
        log.info("THE TASK COMPLETED SUCCESSFULLY: ");
        return TeamMapper.mapToTeamDto(team);
    }

    public List<TeamDto> findAllTeam() {
        return teamRepository.findAll()
                .stream()
                .map(TeamMapper::mapToTeamDto)
                .collect(Collectors.toList());
    }
}
