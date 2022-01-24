package pl.reskilled.menteeManagerMicroservices.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.reskilled.menteeManagerMicroservices.project.domain.SampleProjectDto;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = MockMvcConfig.class)
public class ProjectControllerGetTestMvc implements SampleProjectDto {

    @Test
    void should_return_a_response_200_when_find_all_project(@Autowired MockMvc mockMvc,
                                                            @Autowired ObjectMapper objectMapper) throws Exception {
        final List<ProjectDto> responseHttp = Arrays.asList(pacmanDtoMapper(), secretKeyDtoMapper());
        final String expectedResponse = objectMapper.writeValueAsString(responseHttp);
        MvcResult result = mockMvc.perform(get("/api/projects")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        final String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody.equals(expectedResponse));
    }

}
