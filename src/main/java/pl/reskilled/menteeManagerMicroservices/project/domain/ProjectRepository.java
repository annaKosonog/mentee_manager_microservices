package pl.reskilled.menteeManagerMicroservices.project.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
