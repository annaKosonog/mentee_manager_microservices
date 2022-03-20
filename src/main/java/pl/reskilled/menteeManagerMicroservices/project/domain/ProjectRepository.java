package pl.reskilled.menteeManagerMicroservices.project.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    boolean existsByName(String name);

    Optional<Project> findByName(String name);
}
