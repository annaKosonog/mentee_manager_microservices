package pl.reskilled.menteeManagerMicroservices.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.reskilled.menteeManagerMicroservices.MenteeManagerMicroservices;
import pl.reskilled.menteeManagerMicroservices.user.model.SampleLoginRequestDto;
import pl.reskilled.menteeManagerMicroservices.user.model.SampleUser;
import pl.reskilled.menteeManagerMicroservices.user.security.MessageResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.model.LoginRequestDto;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;
import pl.reskilled.menteeManagerMicroservices.user.security.service.UserService;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MenteeManagerMicroservices.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("container_test")
public class AuthControllerMvcRegisterTest implements SampleLoginRequestDto, SampleUser {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2")
            .withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }

    @Test
    void should_return_new_user_add_to_db(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper, @Autowired UserRepository userRepository) throws Exception {
        then(userRepository.existsByEmail("test@contact.pl")).isFalse();
        final LoginRequestDto beforeToSaveDb = loginRequestDtoWithoutId("Wacek", "test@contact.pl", "test1");
        final String expected = objectMapper.writeValueAsString(beforeToSaveDb);

        final MvcResult createUser = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(StandardCharsets.UTF_8.name())
                .content(expected))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final MessageResponse messageResponse = objectMapper.readValue(createUser.getResponse().getContentAsString(), MessageResponse.class);
        assertThat(messageResponse.getMessage().equals("User registered successfully!"));
        assertThat(userRepository.existsByEmail("test@contact.pl")).isTrue();
    }

    @Test
    void should_return_request_code_http_400_when_will_not_provide_a_username(@Autowired MockMvc mockMvc,
                                                                              @Autowired ObjectMapper objectMapper) throws Exception {
        final LoginRequestDto user = loginRequestDtoWithoutId("", "test@contact.pl", "admin");
        final String signInExpected = objectMapper.writeValueAsString(user);
        final String expectedResponse = "Username may not be blank";

        final MvcResult login = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInExpected))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        final String actualResponse = login.getResponse().getContentAsString();

        assertThat(actualResponse).containsIgnoringCase(expectedResponse);
        assertThat(actualResponse).isNotBlank();

    }

    @Test
    void should_return_request_code_http_400_when_will_not_provide_an_email(@Autowired MockMvc mockMvc,
                                                                            @Autowired ObjectMapper objectMapper) throws Exception {
        final LoginRequestDto user = loginRequestDtoWithoutId("Wacek", "", "admin");
        final String signInExpected = objectMapper.writeValueAsString(user);
        final String expectedResponse = "Email may not be blank";

        final MvcResult login = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInExpected))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        final String actualResponse = login.getResponse().getContentAsString();

        assertThat(actualResponse).containsIgnoringCase(expectedResponse);
        assertThat(actualResponse).isNotBlank();

    }

    @Test
    void should_return_request_code_http_400_when_will_provide_wrong_format_an_email(@Autowired MockMvc mockMvc,
                                                                                     @Autowired ObjectMapper objectMapper) throws Exception {
        final LoginRequestDto user = loginRequestDtoWithoutId("Wacek", "test", "admin");
        final String signInExpected = objectMapper.writeValueAsString(user);
        final String expectedResponse = "must be a well-formed email address";

        final MvcResult login = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInExpected))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        final String actualResponse = login.getResponse().getContentAsString();

        assertThat(actualResponse).containsIgnoringCase(expectedResponse);
        assertThat(actualResponse).isNotBlank();
    }

    @Test
    void should_add_user_in_database_when_email_is_unique(@Autowired UserService userService,
                                                             @Autowired UserRepository userRepository) {
        //GIVEN

        final LoginRequestDto loginRequestDto = userTestDto();
        then(userRepository.existsByEmail("test@contact.pl")).isFalse();

        //WHEN
        final User actual = userService.registerNewUserAccount(loginRequestDto);

        //THEN

        assertThat(loginRequestDto.getEmail()).isEqualTo(actual.getEmail());
        assertThat(userRepository.existsByEmail("test@contact.pl")).isTrue();
    }

    @Test
    void should_return_http_code_400_when_try_to_add_user_without_password(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper, @Autowired UserRepository userRepository) throws Exception {
        final LoginRequestDto user = loginRequestDtoWithoutId("Wacek", "test", "");
        final String signInExpected = objectMapper.writeValueAsString(user);

        final String expectedResponse = "Password may not be blank";

        final MvcResult result = mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInExpected))
                .andExpect(status().isBadRequest())
                .andReturn();

        final String actualResponse = result.getResponse().getContentAsString();
        assertThat(actualResponse).containsIgnoringCase(expectedResponse);
        assertThat(actualResponse).isNotBlank();
    }


}


