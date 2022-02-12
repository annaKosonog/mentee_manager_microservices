package pl.reskilled.menteeManagerMicroservices.teams;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.reskilled.menteeManagerMicroservices.MenteeManagerMicroservices;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeRepository;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectRepository;
import pl.reskilled.menteeManagerMicroservices.teams.domain.Team;
import pl.reskilled.menteeManagerMicroservices.teams.domain.TeamRepository;
import pl.reskilled.menteeManagerMicroservices.teams.domain.TeamService;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamDto;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeUtil.userPawelDao;
import static pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil.pacmanDao;
import static pl.reskilled.menteeManagerMicroservices.teams.domain.TeamUtils.masterDaoNull;
import static pl.reskilled.menteeManagerMicroservices.teams.domain.TeamUtils.masterDtoNull;

@SpringBootTest(classes = MenteeManagerMicroservices.class)
@ActiveProfiles("container_test")
@Testcontainers
public class TeamServiceContainerIntegrationTest {

    private  static final String MONGO_VERSION = "4.4.4";

    @Container
    private static final MongoDBContainer DB_CONTAINER = new MongoDBContainer("mongo:" + MONGO_VERSION);

    static {
        DB_CONTAINER.start();
        System.setProperty("DB_PORT", String.valueOf(DB_CONTAINER.getFirstMappedPort()));
    }

    @Test
    void should_create_new_team_to_db(@Autowired TeamRepository teamRepository,@Autowired ProjectRepository projectRepository,@Autowired MenteeRepository menteeRepository,
                                      @Autowired TeamService service){
        //GIVEN
        final String name = "Pacman_Game";
        final String email = "pawel@contact.pl";
        final TeamDto beforeSaveDb = masterDtoNull();

        final Team teamSavedWithDb = masterDaoNull();
        projectRepository.save(pacmanDao());
        menteeRepository.save(userPawelDao());
        teamRepository.save(teamSavedWithDb);


        //WHEN
        final TeamDto newTeam = service.createNewTeam(beforeSaveDb, name, email);

        //THEN
        assertThat(newTeam).isNotNull();
        assertThat(newTeam.getProject()).isEqualTo(beforeSaveDb.getProject());

    }

}
