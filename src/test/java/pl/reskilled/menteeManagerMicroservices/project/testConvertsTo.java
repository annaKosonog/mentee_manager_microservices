package pl.reskilled.menteeManagerMicroservices.project;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.supercsv.io.CsvBeanWriter;
import pl.reskilled.menteeManagerMicroservices.mentee.MenteeUtil;
import pl.reskilled.menteeManagerMicroservices.mentee.domain.dto.MenteeDto;
import pl.reskilled.menteeManagerMicroservices.project.domain.ProjectUtil;
import pl.reskilled.menteeManagerMicroservices.project.domain.dto.ProjectDto;

import java.io.IOException;
import java.io.StringWriter;

import static org.supercsv.prefs.CsvPreference.STANDARD_PREFERENCE;

public class testConvertsTo {
    @Test
    public void testConvertsToPrimitives() throws IOException {
        ProjectDto character = ProjectUtil.addNewProject();
        String[] mapping = { "name", "developers", "techStack", "description"};

        StringWriter writer = new StringWriter();
        CsvBeanWriter beanWriter = new CsvBeanWriter(writer, STANDARD_PREFERENCE);
        beanWriter.write(character, mapping);
        beanWriter.close();

        String csv = writer.toString();
        Assert.assertNotNull(csv);
    }

    @Test
    public void testConvertsToMentee() throws IOException {
        MenteeDto character = MenteeUtil.menteeAdam();
        String[] mapping = {"username", "email", "future_position", "duration", "seniority"};

        StringWriter writer = new StringWriter();
        CsvBeanWriter beanWriter = new CsvBeanWriter(writer, STANDARD_PREFERENCE);
        beanWriter.write(character, mapping);
        beanWriter.close();

        String csv = writer.toString();
        Assert.assertNotNull(csv);
        Assert.assertEquals("Adam,mentoring@contact.pl,Junior Java Developer,SIX_MONTH,INTERN", csv.trim());
    }

}
