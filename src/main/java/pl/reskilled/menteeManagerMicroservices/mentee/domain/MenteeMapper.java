package pl.reskilled.menteeManagerMicroservices.mentee.domain;

import org.springframework.stereotype.Component;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;


@Component
public class MenteeMapper {

    public MenteeDto mapToMenteeDto(Mentee from) {
        return MenteeDto.builder()
                .username(from.getUsername())
                .email(from.getEmail())
                .future_position(from.getFuture_position())
                .duration(from.getDuration())
                .seniority(from.getSeniority())
                .build();
    }

    public Mentee mapToMentee(MenteeDto reverse) {
        return Mentee.builder()
                .username(reverse.getUsername())
                .email(reverse.getEmail())
                .future_position(reverse.getFuture_position())
                .duration(reverse.getDuration())
                .seniority(reverse.getSeniority())
                .build();
    }
}
