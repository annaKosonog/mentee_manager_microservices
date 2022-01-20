package pl.reskilled.menteeManagerMicroservices.project.domain;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;
import pl.reskilled.menteeManagerMicroservices.project.exception.response.NameExistsException;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private static Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;


    public ProjectDto addNewProject(ProjectDto projectDto) {
        LOGGER.info("Beginning of new project writing to database:  ");
        final Project project = projectMapper.mapToProject(projectDto);
        try {
            projectRepository.save(project);
            LOGGER.info("The project has been saved to the database:  ");
            return projectDto;
        } catch (DuplicateKeyException e) {
            LOGGER.error("Error: Name project is already ");
            throw new NameExistsException(projectDto.getName());
        }
    }
}
