package pl.reskilled.menteeManagerMicroservices.project;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects/add")
    public ResponseEntity<ProjectDto> addNewProjects(@Valid @RequestBody ProjectDto projectDto){
        return ResponseEntity.ok(projectService.addNewProject(projectDto));
    }
}
