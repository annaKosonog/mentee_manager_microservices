package pl.reskilled.menteeManagerMicroservices.teams;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.reskilled.menteeManagerMicroservices.teams.domain.TeamService;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TeamController {

    private final TeamService teamService;


    @PostMapping("/teams/add")
    ResponseEntity<TeamDto> addNewTeam(@Valid @RequestBody TeamDto teamDto,
                                       @RequestParam(name = "name_project") String name,
                                       @RequestParam(name = "email_mentee") String email) {
        return ResponseEntity.ok(teamService.createNewTeam(teamDto, name, email));
    }

    @GetMapping("/teams")
    ResponseEntity<List<TeamDto>> getAllTeam() {
        final List<TeamDto> allTeam = teamService.findAllTeam();
        return ResponseEntity.ok(allTeam);
    }
}
