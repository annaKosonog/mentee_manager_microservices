package pl.reskilled.menteeManagerMicroservices.mentee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeService;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MenteeController {

    private final MenteeService menteeService;


    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority(('MENTOR'))")
    @GetMapping("/students")
    public ResponseEntity<List<MenteeDto>> getAllStudents() {
        return ResponseEntity.ok(menteeService.getAllStudents());
    }


    @PostMapping("/students/add")
    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority(('MENTOR'))")
    public ResponseEntity<MenteeDto> registerAddNewStudents(@Valid @RequestBody MenteeDto menteeDto) {
        return ResponseEntity.ok(menteeService.addNewStudent(menteeDto));
    }
}
