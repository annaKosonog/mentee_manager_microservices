package pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.reskilled.menteeManagerMicroservices.MenteeManagerMicroservices;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleRefreshToken;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.RefreshToken;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MenteeManagerMicroservices.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("container_test")
public class RefreshTokenWithContainerTest implements SampleRefreshToken {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2")
            .withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }


    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(post("/api/signin"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldGenerateAuthToken(@Autowired RefreshTokenService refreshTokenService, @Autowired MockMvc mvc) throws Exception {
        RefreshToken token = refreshTokenService.createRefreshToken(generateSimpleRefreshToken().getUser().getId());

        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/refreshtoken").header("Authorization", token)).andExpect(status().isOk());
    }

}
