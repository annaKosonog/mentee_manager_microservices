package pl.reskilled.menteeManagerMicroservices.user.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.reskilled.menteeManagerMicroservices.user.security.model.student.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    boolean existsByEmail(String email);
}
