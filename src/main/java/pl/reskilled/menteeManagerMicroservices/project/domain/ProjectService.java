package pl.reskilled.menteeManagerMicroservices.project.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;
import pl.reskilled.menteeManagerMicroservices.project.exception.response.NameExistsException;
import pl.reskilled.menteeManagerMicroservices.teams.exception.ProjectNameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;


    public ProjectDto addNewProject(ProjectDto projectDto) {
        log.info("Beginning of new project writing to database:  ");
        final Project project = ProjectMapper.mapToProject(projectDto);
        try {
            projectRepository.save(project);
            log.info("The project has been saved to the database:  ");
            log.info("----------------------------------------------");
            return ProjectMapper.mapToProjectDto(project);
        } catch (DuplicateKeyException e) {
            log.error("Error: Name project is already ");
            throw new NameExistsException(projectDto.getName());
        }
    }

    public List<ProjectDto> findAllProject() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::mapToProjectDto)
                .collect(Collectors.toList());
    }

    public ProjectDto findProjectByName(String name) {
        log.info("Search for the project by name: ");
        final Optional<Project> searchProjectByName = projectRepository.findByName(name);
        log.info("Project found with given project name: " + searchProjectByName);
        searchProjectByName.orElseThrow(() -> new ProjectNameNotFoundException(name));
        log.error("Project: Project name not found exception " + name);
        return ProjectMapper.mapToProjectDto(searchProjectByName.get());
    }
}

