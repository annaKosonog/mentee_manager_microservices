package pl.reskilled.menteeManagerMicroservices.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.reskilled.menteeManagerMicroservices.MenteeManagerMicroservices;
import pl.reskilled.menteeManagerMicroservices.user.mapper.SampleUserDto;
import pl.reskilled.menteeManagerMicroservices.user.model.SampleUser;
import pl.reskilled.menteeManagerMicroservices.user.security.model.User;
import pl.reskilled.menteeManagerMicroservices.user.security.model.LoginRequestDto;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;
import pl.reskilled.menteeManagerMicroservices.user.security.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest(classes = MenteeManagerMicroservices.class)
@ActiveProfiles("container")
@Testcontainers
public class UserServiceAddNewUserTestIntegration implements SampleUser, SampleUserDto {

    @Container
    private static final MongoDBContainer container = new MongoDBContainer("mongo:4.2")
            .withExposedPorts(27017);

    static {
        container.start();
        System.setProperty("DB_PORT", String.valueOf(container.getFirstMappedPort()));
    }

    @Test
    void should_add_user_in_database_when_email_is_unique(@Autowired UserService userService, @Autowired UserRepository userRepository) {
        //GIVEN
        final User beforeSaveToDb = userParameterWithoutId("Wacek", "exists_email", "test1");
        userRepository.save(beforeSaveToDb);

        final LoginRequestDto loginRequestDto = afterSaveDb();
        then(userRepository.existsByEmail("exists_email")).isTrue();

        //WHEN
        final User checkUserEmailBeforeSaveDb = userService.registerNewUserAccount(loginRequestDto);

        //THEN
        assertThat(loginRequestDto.getEmail()).isEqualTo(checkUserEmailBeforeSaveDb.getEmail());
        assertThat(userRepository.existsByEmail("test@example.pl")).isTrue();
    }
}
