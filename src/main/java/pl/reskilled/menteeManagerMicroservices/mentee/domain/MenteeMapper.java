package pl.reskilled.menteeManagerMicroservices.mentee.domain;

import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Duration;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Seniority;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;


@Component
public class MenteeMapper {

    public MenteeDto mapToMenteeDto(Mentee from) {
        return MenteeDto.builder()
                .username(from.getUsername())
                .email(from.getEmail())
                .future_position(from.getFuture_position())
                .duration(from.getDuration().toString())
                .seniority(from.getSeniority().toString())
                .build();
    }

    public Mentee mapToMentee(MenteeDto reverse) {
        return Mentee.builder()
                .username(reverse.getUsername())
                .email(reverse.getEmail())
                .future_position(reverse.getFuture_position())
                .duration(Duration.valueOf(reverse.getDuration()))
                .seniority(Seniority.valueOf(reverse.getSeniority()))
                .build();
    }
}
