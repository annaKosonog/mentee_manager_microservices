package pl.reskilled.menteeManagerMicroservices.mentee.domain;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;
import pl.reskilled.menteeManagerMicroservices.user.security.exception.UserExistEmailException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenteeService {

    private final MenteeRepository menteeRepository;
    private final MenteeMapper menteeMapper;

    private static Logger LOGGER = LoggerFactory.getLogger(MenteeService.class);

    public List<MenteeDto> getAllStudents() {
        return menteeRepository.findAll()
                .stream()
                .map(menteeMapper::mapToMenteeDto)
                .collect(Collectors.toList());
    }

    public MenteeDto addNewStudent(MenteeDto menteeDto) {
        LOGGER.info("Beginning of new student writing to database:  ");
        final Mentee mentee = menteeMapper.mapToMentee(menteeDto);
        try {
            menteeRepository.save(mentee);
            LOGGER.info("The student has been registered in the database");
            return menteeDto;
        } catch (DuplicateKeyException e) {
            LOGGER.error("Error: Email is already in database");
            throw new UserExistEmailException(menteeDto.getEmail());
        }
    }
}
