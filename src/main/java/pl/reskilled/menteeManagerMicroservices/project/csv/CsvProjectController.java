package pl.reskilled.menteeManagerMicroservices.project.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeService;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CsvProjectController {

    private final ProjectService projectService;
    private final MenteeService menteeService;


    @GetMapping("/csv/project")
    public void returningCsvContentFromAnApiProject(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=project_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);


        List<ProjectDto> listProject = projectService.findAllProject();

        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8.toString());
        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);


        String[] csvHeader = {"Name", "Developers", "Tech stack", "Description"};
        String[] nameMapping = {"name", "developers", "techStack", "description"};

        csvWriter.writeHeader(csvHeader);

        for (ProjectDto dto : listProject) {
            csvWriter.write(dto, nameMapping);

        }
        csvWriter.close();
    }


    @GetMapping("/csv/mentees")
    public void returningCsvContentFromAnApiMentees(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=mentees_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);


        List<MenteeDto> listProject = menteeService.getAllStudents();

        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8.toString());
        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);


        String[] csvHeader = {"Username", "Email", "Future_position", "Duration", "Seniority"};
        String[] nameMapping = {"username", "email", "future_position", "duration", "seniority"};

        csvWriter.writeHeader(csvHeader);

        for (MenteeDto dto : listProject) {
            csvWriter.write(dto, nameMapping);

        }
        csvWriter.close();
    }
}
