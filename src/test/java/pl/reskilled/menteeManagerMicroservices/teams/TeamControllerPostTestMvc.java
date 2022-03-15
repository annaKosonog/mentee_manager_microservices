package pl.reskilled.menteeManagerMicroservices.teams;

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
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeMapper;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeService;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectMapper;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.teams.domain.TeamMapper;
import pl.reskilled.menteeManagerMicroservices.teams.domain.TeamRepository;
import pl.reskilled.menteeManagerMicroservices.teams.domain.TeamService;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamReadDto;
import pl.reskilled.menteeManagerMicroservices.user.security.WebSecurityConfig;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.jwt.JwtTestConfig;

import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.reskilled.menteeManagerMicroservices.teams.domain.TeamUtils.masterDto;
import static pl.reskilled.menteeManagerMicroservices.teams.domain.TeamUtils.masterTeamReadDto;
import static pl.reskilled.menteeManagerMicroservices.teams.domain.TeamUtils.vipTeamReadDto;

@WebMvcTest
@ContextConfiguration(classes = MockMvcConfig.class)
@AutoConfigureMockMvc(addFilters = false)
public class TeamControllerPostTestMvc {

    @Test
    void should_return_a_response_code_200_when_add_new_team(@Autowired MockMvc mockMvc,
                                                             @Autowired ObjectMapper objectMapper) throws Exception {
        final String name = "Pacman_Game";
        final String email = "pawel@contact.pl";
        final TeamDto newTeam = masterDto();
        final String expectedResponse = objectMapper.writeValueAsString(newTeam);

        final MvcResult result = mockMvc.perform(post("/api/teams/add")
                .param("name_project", name)
                .param("email_mentee", email)
                .contentType(MediaType.APPLICATION_JSON)
                .content(expectedResponse))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @Test
    void should_return_status_not_found_and_blank_response(@Autowired MockMvc mockMvc) throws Exception {
        final String name = "Pacman_Game";
        final String email = "pawel@contact.pl";

        final MvcResult result = mockMvc.perform(post("/api/teams/add")
                .param("name_project", name)
                .param("email_mentee", email)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(wrongProjectPostJson()))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andReturn();
        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody).contains(expectedErrorMessages());
    }

    private List<String> expectedErrorMessages() {
        return Arrays.asList(
                "name.not.blank");
    }

    private String wrongProjectPostJson() {
        return "{\"project\":[\"test\", \"test2\"]," +
                "\"members\" : [\"test\", \"test2\"]}";
    }
}

@Import({MessageSourceConfig.class, WebSecurityConfig.class, JwtTestConfig.class})
class MockMvcConfig {

    @Bean
    TeamService teamService() {
        TeamRepository teamRepository = mock(TeamRepository.class);
        MenteeService menteeService = mock(MenteeService.class);
        ProjectService projectService = mock(ProjectService.class);
        ProjectMapper projectMapper = new ProjectMapper();
        MenteeMapper menteeMapper = new MenteeMapper();
        TeamMapper teamMapper = new TeamMapper(projectMapper, menteeMapper);

        return new TeamService(teamRepository, menteeService, projectService, teamMapper ) {
            @Override
            public TeamDto createNewTeam(TeamDto teamDto, String name, String email) {
                return masterDto();
            }

            @Override
            public List<TeamReadDto> findAllTeams() {
                return Arrays.asList(masterTeamReadDto(), vipTeamReadDto());
            }
        };
    }


    @Bean
    TeamController teamController(TeamService service) {
        return new TeamController(service);
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

