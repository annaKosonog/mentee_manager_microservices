package pl.reskilled.menteeManagerMicroservices.project.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;
import pl.reskilled.menteeManagerMicroservices.project.exception.response.NameExistsException;
import pl.reskilled.menteeManagerMicroservices.teams.exception.ProjectNameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;


    public ProjectDto addNewProject(ProjectDto projectDto) {
        log.info("Beginning of new project writing to database:  ");
        final Project project = projectMapper.mapToProject(projectDto);
        try {
            projectRepository.save(project);
            log.info("The project has been saved to the database:  ");
            log.info("----------------------------------------------");
            return projectMapper.mapToProjectDto(project);
        } catch (DuplicateKeyException e) {
            log.error("Error: Name project is already ");
            throw new NameExistsException(projectDto.getName());
        }
    }

    @Transactional
    public List<ProjectDto> findAllProject() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::mapToProjectDto)
                .collect(Collectors.toList());
    }

    public ProjectDto findProjectByName(String name) {
        log.info("Search for the project by name: ");
        return projectRepository.findByName(name)
                .map(projectMapper::mapToProjectDto)
                .orElseThrow(() -> new ProjectNameNotFoundException(name));
    }
}

