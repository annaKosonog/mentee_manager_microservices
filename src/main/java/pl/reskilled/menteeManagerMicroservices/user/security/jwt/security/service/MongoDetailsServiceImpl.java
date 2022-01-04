package pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.reskilled.menteeManagerMicroservices.user.security.domain.dao.User;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MongoDetailsServiceImpl implements UserDetailsService {

    private static final String USER_NOT_FOUND = "User not found: ";
    private final UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + email));

        return UserDetailsImpl.getUser(user);
    }
}
