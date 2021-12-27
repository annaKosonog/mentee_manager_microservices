package pl.reskilled.menteeManagerMicroservices.user.security.mapper;

import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.user.security.model.student.Student;
import pl.reskilled.menteeManagerMicroservices.user.security.model.student.StudentDto;

@Component
public class StudentMapper {

    public StudentDto mapToStudentDto(Student from) {
        return StudentDto.builder()
                .username(from.getUsername())
                .email(from.getEmail())
                .future_position(from.getFuture_position())
                .duration(from.getDuration())
                .seniority(from.getSeniority())
                .build();
    }

    public Student mapToStudent(StudentDto reverse) {
        return Student.builder()
                .username(reverse.getUsername())
                .email(reverse.getEmail())
                .future_position(reverse.getFuture_position())
                .duration(reverse.getDuration())
                .seniority(reverse.getSeniority())
                .build();
    }
}
