package pl.reskilled.menteeManagerMicroservices.user.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.RefreshToken;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.User;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);

    int deleteByUser(User user);
}
