package pl.reskilled.menteeManagerMicroservices.user.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.User;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MongoDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private static final String USER_NOT_FOUND = "User not found: ";

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND + email));

        return UserDetailsImpl.build(user);
    }
}
