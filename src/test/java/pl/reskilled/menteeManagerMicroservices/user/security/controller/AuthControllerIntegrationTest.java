package pl.reskilled.menteeManagerMicroservices.user.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.reskilled.menteeManagerMicroservices.MenteeManagerMicroservices;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleLoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleSignUp;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleUser;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.Authority;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.LoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.SignUpDto;
import pl.reskilled.menteeManagerMicroservices.user.security.payload.response.JwtResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.payload.response.MessageResponse;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MenteeManagerMicroservices.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("container_test")
public class AuthControllerIntegrationTest implements SampleSignUp, SampleUser, SampleLoginDto {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2")
            .withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }


    @Test
    void should_return_code_http_200(@Autowired MockMvc mockMvc,
                                     @Autowired ObjectMapper objectMapper,
                                     @Autowired UserRepository userRepository) throws Exception {

  //      assertThat(userRepository.findByEmail("soki@hortex.pl")).isNotEmpty();

        final LoginDto user = allParameterLoginDto("soki@hortex.pl", "Jan123");
        final String signInException = objectMapper.writeValueAsString(user);

        final MvcResult createUser = mockMvc.perform(post("/api/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInException))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        final String contentWithHttp = createUser.getResponse().getContentAsString();
        final JwtResponse actualResponse = objectMapper.readValue(contentWithHttp, JwtResponse.class);
        String token = actualResponse.getToken();

        mockMvc.perform(get("/api/students")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void should_return_offers_response_401_unauthorized_after_negative_authentication_with_jwt_token(@Autowired MockMvc mockMvc,
                                                                                                            @Autowired UserRepository userRepository,
                                                                                                            @Autowired ObjectMapper objectMapper) throws Exception {

        final MessageResponse expectedLoginErrorResponse = new MessageResponse(
                "Bad Credentials",
                HttpStatus.UNAUTHORIZED
        );

        assertThat(userRepository.findByEmail("soki@hotex.pl")).isEmpty();

        final LoginDto user = allParameterLoginDto("soki@hotex.pl", "Jan123");
        final String body = objectMapper.writeValueAsString(user);


        MvcResult result = mockMvc.perform(post("/api/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isUnauthorized())
                .andReturn();

        final String content = result.getResponse().getContentAsString();
        final MessageResponse messageResponse = objectMapper.readValue(content, MessageResponse.class);

        assertThat(messageResponse).isEqualTo(expectedLoginErrorResponse);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }


    @Test
    public void should_return_400_unauthorized_when_username_or_password_is_not_valid(@Autowired MockMvc mockMvc,
                                                                                      @Autowired UserRepository userRepository) throws Exception {
        AssertionsForClassTypes.assertThat(userRepository.findByEmail("email@example.pl")).isEmpty();
        String body = getUser("1234dsa", "Not@ExistentUser", "wacek", "password", Collections.singleton(Authority.STUDENT)).toString();

        mockMvc.perform(post("/api/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void should_return_request_code_http_400_when_will_not_provide_a_username(@Autowired MockMvc mockMvc,
                                                                              @Autowired ObjectMapper objectMapper) throws Exception {
        final SignUpDto user = allParameterSignUpDto("", "test@contact.pl", "admin", Collections.singleton("MENTOR"));
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
        final SignUpDto user = allParameterSignUpDto("Wacek", "", "admin", Collections.singleton("MENTOR"));
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
        final SignUpDto user = allParameterSignUpDto("Wacek", "test", "admin", Collections.singleton("MENTOR"));
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
    void should_return_http_code_400_when_try_to_add_user_without_password(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper, @Autowired UserRepository userRepository) throws Exception {
        final SignUpDto user = allParameterSignUpDto("Wacek", "test", "", Collections.singleton("MENTOR"));
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



