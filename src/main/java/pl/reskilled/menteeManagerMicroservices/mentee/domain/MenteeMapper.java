package pl.reskilled.menteeManagerMicroservices.mentee.domain;

import lombok.experimental.UtilityClass;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Duration;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Mentee;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dao.Seniority;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;

@UtilityClass
public class MenteeMapper {

    public MenteeDto mapToMenteeDto(Mentee from) {
        return MenteeDto.builder()
                .username(from.getUsername())
                .email(from.getEmail())
                .futurePosition(from.getFuturePosition())
                .duration(from.getDuration().toString())
                .seniority(from.getSeniority().toString())
                .build();
    }

    public Mentee mapToMentee(MenteeDto reverse) {
        return Mentee.builder()
                .username(reverse.getUsername())
                .email(reverse.getEmail())
                .futurePosition(reverse.getFuturePosition())
                .duration(Duration.valueOf(reverse.getDuration()))
                .seniority(Seniority.valueOf(reverse.getSeniority()))
                .build();
    }
}
