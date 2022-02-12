package pl.reskilled.menteeManagerMicroservices.teams;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.reskilled.menteeManagerMicroservices.teams.domain.TeamUtils.masterDto;
import static pl.reskilled.menteeManagerMicroservices.teams.domain.TeamUtils.vipDto;

@WebMvcTest
@ContextConfiguration(classes = MockMvcConfig.class)
public class TeamControllerGetTestMvc {

    @Test
    void should_return_a_response_200_when_find_all_project(@Autowired MockMvc mockMvc,
                                                            @Autowired ObjectMapper objectMapper) throws Exception {
        final List<TeamDto> responseHttp = Arrays.asList(masterDto(), vipDto());
        final String expectedResponse = objectMapper.writeValueAsString(responseHttp);
        MvcResult result = mockMvc.perform(get("/api/teams")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        final String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody.equals(expectedResponse));
    }
}
