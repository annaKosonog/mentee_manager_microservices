package pl.reskilled.menteeManagerMicroservices.project;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import pl.reskilled.menteeManagerMicroservices.project.domain.Project;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectRepository;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.addNewProject;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.developerSet;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.firstProjectWithoutId;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.pacman;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.pacmanDtoMapper;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.secretKey;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.secretKeyDtoMapper;

public class ProjectServiceUnitTest{

    ProjectRepository projectRepository = mock(ProjectRepository.class);

    ProjectService projectService = new ProjectService(projectRepository);

    @Test
    public void should_add_new_project_db() {
        //GIVEN
        final ProjectDto beforeSaveToDb = addNewProject();
        final Project projectJustBeforeSavingWithoutId = firstProjectWithoutId();

        final Project savedProject = secretKey();

        when(projectRepository.save(projectJustBeforeSavingWithoutId)).thenReturn(savedProject);

        //WHEN
        final ProjectDto saveResult = projectService.addNewProject(beforeSaveToDb);

        //THEN
        then(saveResult.getDevelopers()).isEqualTo(developerSet());
        verify(projectRepository, times(1)).save(any());
    }

    @Test
    public void should_return_all_project_with_db(){
        //GIVEN
        when(projectRepository.findAll()).thenReturn(Arrays.asList(secretKey(), pacman()));

        //WHEN
        final List<ProjectDto> allProject = projectService.findAllProject();
        //THEN
        assertThat(allProject).isEqualTo(Arrays.asList(secretKeyDtoMapper(), pacmanDtoMapper()));
    }

    @Test
    public void should_return_list_all_project_about_size_2(){
        //GIVEN
        when(projectRepository.findAll()).thenReturn(Arrays.asList(secretKey(), pacman()));

        //WHEN
        final List<ProjectDto> allProject = projectService.findAllProject();
        //THEN
        MatcherAssert.assertThat(allProject, hasSize(2));
    }
}
