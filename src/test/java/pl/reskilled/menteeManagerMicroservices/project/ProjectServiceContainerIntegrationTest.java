package pl.reskilled.menteeManagerMicroservices.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.reskilled.menteeManagerMicroservices.MenteeManagerMicroservices;
import pl.reskilled.menteeManagerMicroservices.project.domain.Project;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectRepository;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.project.domain.SampleProject;
import pl.reskilled.menteeManagerMicroservices.project.domain.SampleProjectDto;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;
import pl.reskilled.menteeManagerMicroservices.project.exception.response.NameExistsException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes = ProjectServiceContainerIntegrationTest.TestConfig.class)
@ActiveProfiles("container_test")
@Testcontainers
public class ProjectServiceContainerIntegrationTest implements SampleProject, SampleProjectDto {

    private static final String MONGO_VERSION = "4.4.4";

    @Container
    private static final MongoDBContainer DB_CONTAINER = new MongoDBContainer("mongo:" + MONGO_VERSION);

    static {
        DB_CONTAINER.start();
        System.setProperty("DB_PORT", String.valueOf(DB_CONTAINER.getFirstMappedPort()));
    }

    @Test
    void should_thrown_an_exception_when_we_want_save_a_duplicate_name_project(@Autowired ProjectRepository repository,
                                                                      @Autowired ProjectService service) {
        //GIVEN

        final ProjectDto shouldNotAddProject = allParameterProjectDto("NOT_UNIQUE", Collections.singleton("first_test"), Collections.singleton("first_test"), "NOT_EXISTS");
        final Project existingProject = allParametersForProject("1234", "NOT_UNIQUE", Collections.singleton("third_test"), Collections.singleton("third_test"), "EXISTS");
        repository.save(existingProject);

        then(repository.existsByName("NOT_UNIQUE")).isTrue();

        //WHEN
        Throwable throwable = catchThrowable(()-> service.addNewProject(shouldNotAddProject));

        //THEN
        assertThat(throwable)
                .isInstanceOf(NameExistsException.class)
                .hasMessage("The name is exists to the db: " + shouldNotAddProject.getName());
        assertThat(repository.existsByName("NOT_UNIQUE")).isTrue();
    }


    @Test
    void should_save_name_project_in_the_databases_when_name_is_unique(@Autowired ProjectRepository repository,
                                                                       @Autowired ProjectService service){
        //GIVEN
        final ProjectDto shouldAddProject = allParameterProjectDto("UNIQUE", Collections.singleton("first_test"), Collections.singleton("first_test"), "NOT_EXISTS");
        final Project existingProject = allParametersForProject("1234", "NOT_UNIQUE", Collections.singleton("third_test"), Collections.singleton("third_test"), "EXISTS");
        repository.save(existingProject);
        then(repository.existsByName("NOT_UNIQUE")).isTrue();

        //WHEN
        final ProjectDto savedDb = service.addNewProject(shouldAddProject);

        //THEN
        assertThat(savedDb.getName().equals("UNIQUE"));
        assertThat(repository.existsByName("UNIQUE")).isTrue();

    }

    @Import(MenteeManagerMicroservices.class)
    static class TestConfig {

    }
}
