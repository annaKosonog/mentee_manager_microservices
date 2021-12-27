package pl.reskilled.menteeManagerMicroservices.user.security.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import pl.reskilled.menteeManagerMicroservices.user.exception.api.response.UserExistEmailException;
import pl.reskilled.menteeManagerMicroservices.user.security.mapper.StudentMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.mapper.UserMapper;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.SignUpDto;
import pl.reskilled.menteeManagerMicroservices.user.security.model.registration.User;
import pl.reskilled.menteeManagerMicroservices.user.security.model.student.Student;
import pl.reskilled.menteeManagerMicroservices.user.security.model.student.StudentDto;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.StudentRepository;
import pl.reskilled.menteeManagerMicroservices.user.security.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final UserMapper userMapper;
    private final StudentMapper studentMapper;


    public User registerNewUserAccount(SignUpDto signUpDto) {
        final User user = userMapper.mapRegisterToUser(signUpDto);
        return userRepository.save(user);
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    public StudentDto addNewStudent(StudentDto studentDto) {
        LOGGER.info("Beginning of new student writing to database:  ");
        final Student student = studentMapper.mapToStudent(studentDto);
        try {
            studentRepository.save(student);
            LOGGER.info("The student has been registered in the database");
            return studentDto;
        } catch (DuplicateKeyException e) {
            LOGGER.error("Error: Email is already in database");
            throw new UserExistEmailException(studentDto.getEmail());
        }
    }

}
