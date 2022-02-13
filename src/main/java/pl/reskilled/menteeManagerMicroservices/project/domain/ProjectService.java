package pl.reskilled.menteeManagerMicroservices.project.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;
import pl.reskilled.menteeManagerMicroservices.project.exception.response.NameExistsException;
import pl.reskilled.menteeManagerMicroservices.teams.exception.ProjectNotFoundException;

import java.util.List;
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
        log.info("----------------------------------");
        log.info("SEARCH FOR THE PROJECT BY NAME: ");
        try {
            final Project searchProjectByName = projectRepository.findByName(name);
            log.info("PROJECT FOUND WITH GIVEN PROJECT_NAME: " + searchProjectByName);
            return ProjectMapper.mapToProjectDto(searchProjectByName);
        } catch (ProjectNotFoundException e) {
            log.error("PROJECT: PROJECT_NAME NOT FOUND EXCEPTION " + e.getMessage());
            throw new ProjectNotFoundException(e.getName());
        }
    }
}
