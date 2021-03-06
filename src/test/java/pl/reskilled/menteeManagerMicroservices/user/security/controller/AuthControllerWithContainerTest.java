package pl.reskilled.menteeManagerMicroservices.user.security.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.reskilled.menteeManagerMicroservices.MenteeManagerMicroservices;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleLoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleUser;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.LoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.payload.response.JwtResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MenteeManagerMicroservices.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("container_test")
public class AuthControllerWithContainerTest implements SampleUser, SampleLoginDto {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2")
            .withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }

    @Test
    void should_return_generate_token_the_next_to_should_have_access_request_http_200(@Autowired MockMvc mvc,
                                                                                      @Autowired ObjectMapper objectMapper) throws Exception {
        final LoginDto register = userExistsWithDb();
        final String loginInExpected = objectMapper.writeValueAsString(register);

        final MvcResult login = mvc.perform(post("/api/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginInExpected))
                .andDo(log())
                .andExpect(status().isOk())
                .andReturn();

        final String token = login.getResponse().getContentAsString();

        final JwtResponse actualResponse = objectMapper.readValue(token, JwtResponse.class);
        assertThat(actualResponse.getEmail()).isEqualTo("soki@hortex.pl");
        assertThat(actualResponse.getToken()).isNotBlank();

    }

    @Test
    void should_return_request_code_http_400_when_will_not_provide_a_email(@Autowired MockMvc mockMvc,
                                                                              @Autowired ObjectMapper objectMapper) throws Exception {
        final LoginDto user = userTestDto();
        final String signInExpected = objectMapper.writeValueAsString(user);
        final String expectedResponse = "Email may not be blank";

        final MvcResult login = mockMvc.perform(post("/api/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInExpected))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        final String actualResponse = login.getResponse().getContentAsString();

        assertThat(actualResponse).containsIgnoringCase(expectedResponse);
        assertThat(actualResponse).isNotBlank();
    }
}
