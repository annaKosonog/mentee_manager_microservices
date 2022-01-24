package pl.reskilled.menteeManagerMicroservices.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import pl.reskilled.menteeManagerMicroservices.config.MessageSourceConfig;
import pl.reskilled.menteeManagerMicroservices.exceptions.ApiValidationErrorHandler;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectRepository;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.project.domain.SampleProjectDto;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;
import pl.reskilled.menteeManagerMicroservices.project.exception.response.ProjectControllerHandler;
import pl.reskilled.menteeManagerMicroservices.user.security.WebSecurityConfig;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.jwt.JwtTestConfig;

import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = MockMvcConfig.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProjectControllerPostTestMvc implements SampleProjectDto {

    @Test
    void should_return_a_response_code_200_when_add_new_project(@Autowired MockMvc mockMvc,
                                                                @Autowired ObjectMapper objectMapper) throws Exception {
        final ProjectDto newProject = secondNewProjectDto();
        final String expectedResponse = objectMapper.writeValueAsString(newProject);

        final MvcResult result = mockMvc.perform(post("/api/projects/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedResponse))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponse);

    }

    @Test
    void should_return_status_not_found_and_blank_response(@Autowired MockMvc mockMvc) throws Exception {
        final MvcResult result = mockMvc.perform(post("/api/projects/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(wrongProjectPostJson()))
                .andExpect(status().isBadRequest())
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody).contains(expectedErrorMessages());
    }

    private List<String> expectedErrorMessages() {
        return Arrays.asList(
                "name.not.blank",
                "description.not.blank");
    }

    private String wrongProjectPostJson() {
        return "{\"developers\":[\"test\", \"test2\"]," +
                "\"techStack\" : [\"test\", \"test2\"]}";
    }
}

@Import({MessageSourceConfig.class, WebSecurityConfig.class, JwtTestConfig.class})
class MockMvcConfig implements SampleProjectDto {

    @Bean
    ProjectService projectService() {
        ProjectRepository projectRepository = mock(ProjectRepository.class);
        return new ProjectService(projectRepository) {
            @Override
            public ProjectDto addNewProject(ProjectDto projectDto) {
                return secondNewProjectDto();
            }
        };
    }

    @Bean
    ProjectController projectController(ProjectService service) {
        return new ProjectController(service);
    }

    @Bean
    ProjectControllerHandler projectControllerHandler() {
        return new ProjectControllerHandler();
    }

    @Bean
    ApiValidationErrorHandler apiValidationErrorHandler() {
        return new ApiValidationErrorHandler();
    }

    @Bean
    public Validator validatorFactory() {
        return new LocalValidatorFactoryBean();
    }
}

