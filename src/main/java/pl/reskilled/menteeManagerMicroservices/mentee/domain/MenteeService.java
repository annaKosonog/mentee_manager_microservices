package pl.reskilled.menteeManagerMicroservices.mentee.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;
import pl.reskilled.menteeManagerMicroservices.teams.exception.MenteeNotFoundException;
import pl.reskilled.menteeManagerMicroservices.teams.exception.ProjectNotFoundException;
import pl.reskilled.menteeManagerMicroservices.user.security.exception.UserExistEmailException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MenteeService {

    private final MenteeRepository menteeRepository;

    public List<MenteeDto> findAllStudents() {
        return menteeRepository.findAll()
                .stream()
                .map(MenteeMapper::mapToMenteeDto)
                .collect(Collectors.toList());
    }

    public MenteeDto addNewStudent(MenteeDto menteeDto) {
        log.info("Beginning of new student writing to database:  ");
        final Mentee mentee = MenteeMapper.mapToMentee(menteeDto);
        try {
            menteeRepository.save(mentee);
            log.info("The student has been registered in the database");
            return menteeDto;
        } catch (DuplicateKeyException e) {
            log.error("Error: Email is already in database");
            throw new UserExistEmailException(menteeDto.getEmail());
        }
    }

    public MenteeDto findMenteeByEmail(String email) {
        log.info("-----------------------------------");
        log.info("SEARCH FOR THE MENTEE BY EMAIL:");
        try {
            final Mentee searchMenteeByEmail = menteeRepository.findByEmail(email);
            log.info("MENTEE FOUND WITH GIVEN EMAIL_MENTEE: " + searchMenteeByEmail);
            return MenteeMapper.mapToMenteeDto(searchMenteeByEmail );
        } catch (ProjectNotFoundException e) {
            log.error("ERROR: MENTEE WITH GIVEN EMAIL_MENTEE DOES NOT EXIST IN THE DATABASE: " + e.getMessage());
            throw new MenteeNotFoundException(e.getName());
        }
    }
}
