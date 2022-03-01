package pl.reskilled.menteeManagerMicroservices.mentee.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;

import java.util.Optional;

@Repository
public interface MenteeRepository extends MongoRepository<Mentee, String> {

    boolean existsByEmail(String email);

    Optional<Mentee> findByEmail(String email);
}
