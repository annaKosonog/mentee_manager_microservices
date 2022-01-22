package pl.reskilled.menteeManagerMicroservices.project;

import org.junit.jupiter.api.Test;
import pl.reskilled.menteeManagerMicroservices.project.domain.Project;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectRepository;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.project.domain.SampleProject;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProjectServiceUnitTest implements SampleProject {

    ProjectRepository projectRepository = mock(ProjectRepository.class);

    ProjectService projectService = new ProjectService(projectRepository);

    @Test
    public void should_add_new_project_db() {
        //GIVEN
        final ProjectDto beforeSaveToDb = addNewProject();
        final Project projectJustBeforeSavingWithoutId = firstProjectWithoutId();

        final Project savedProject = secondAddProject();

        when(projectRepository.save(projectJustBeforeSavingWithoutId)).thenReturn(savedProject);

        //WHEN
        final ProjectDto saveResult = projectService.addNewProject(beforeSaveToDb);

        //THEN
        then(saveResult.getDevelopers()).isEqualTo(developerSet());
        verify(projectRepository, times(1)).save(any());
    }
}
