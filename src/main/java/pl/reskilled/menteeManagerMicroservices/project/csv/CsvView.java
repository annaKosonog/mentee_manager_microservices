package pl.reskilled.menteeManagerMicroservices.project.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.MenteeService;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectService;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;
import pl.reskilled.menteeManagerMicroservices.teams.domain.TeamService;
import pl.reskilled.menteeManagerMicroservices.teams.domain.dto.TeamReadDto;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CsvView {

    private final ProjectService projectService;
    private final MenteeService menteeService;
    private final TeamService teamService;

    protected void prepareResponse(HttpServletResponse response, File fileName) {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName + "_" + currentDateTime + ".csv";

        response.setHeader(headerKey, headerValue);
    }

    protected void buildCsvDocumentProject(HttpServletResponse response) throws IOException {

        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
        writer.write('\uFEFF');
        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

        List<ProjectDto> listProject = projectService.findAllProject();

        String[] csvHeader = {"Name", "Developers", "Tech stack", "Description"};
        String[] nameMapping = {"name", "developers", "techStack", "description"};
        csvWriter.writeHeader(csvHeader);

        for (ProjectDto dto : listProject) {
            csvWriter.write(dto, nameMapping);
        }
        csvWriter.close();
    }

    protected void buildCsvDocumentMentee(HttpServletResponse response) throws IOException {
        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8.toString());
        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

        List<MenteeDto> listMentee = menteeService.findAllStudents();

        String[] csvHeader = {"Username", "Email", "Future_position", "Duration", "Seniority"};
        String[] nameMapping = {"username", "email", "future_position", "duration", "seniority"};

        csvWriter.writeHeader(csvHeader);

        for (MenteeDto dto : listMentee) {
            csvWriter.write(dto, nameMapping);
        }
        csvWriter.close();
    }

    protected void buildCsvDocumentTeams(HttpServletResponse response) throws IOException {
        Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8.toString());
        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

        List<TeamReadDto> listTeams = teamService.findAllTeams();

        String[] csvHeader = {"Name", "Tech stack", "Projects", "Members"};
        String[] nameMapping = {"name", "techStack", "projects", "members"};

        csvWriter.writeHeader(csvHeader);

        for (TeamReadDto dto : listTeams) {
            csvWriter.write(dto, nameMapping);
        }
        csvWriter.close();
    }
}
