package pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.reskilled.menteeManagerMicroservices.MenteeManagerMicroservices;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleLoginDto;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleSignUp;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.SampleUser;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dto.SignUpDto;
import pl.reskilled.menteeManagerMicroservices.user.security.exception.UserExistEmailException;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest(classes = MenteeManagerMicroservices.class)
@ActiveProfiles("container_test")
@Testcontainers
public class UserServiceAddNewUserWithContainerTest implements SampleUser, SampleLoginDto, SampleSignUp {

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
        final SignUpDto userToWriteDb = registerUser();
        then(userRepository.existsByEmail("soki@hortex.pl")).isFalse();
        userService.registerNewUserAccount(userToWriteDb);
    }

    @Test
    void should_not_add_user_in_database_when_email_is_unique(@Autowired UserService userService, @Autowired UserRepository userRepository) {
        //GIVEN
        final SignUpDto userToWriteDb = registerUser();
        then(userRepository.existsByEmail("soki@hortex.pl")).isFalse();
        userService.registerNewUserAccount(userToWriteDb);


        final SignUpDto existsUsernameDb = registerUser();
        //WHEN
        Throwable throwable = catchThrowable(() -> userService.registerNewUserAccount(existsUsernameDb));

        //THEN
        assertThat(throwable).isInstanceOf(UserExistEmailException.class)
                .hasMessage("The given email already exists in the database: " + existsUsernameDb.getEmail());
        assertThat(userRepository.existsByEmail("soki@hortex.pl")).isTrue();
    }
}
