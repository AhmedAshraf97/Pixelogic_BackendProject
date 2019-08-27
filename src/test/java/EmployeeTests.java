import com.pixelogic.EmpApp.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.pixelogic.EmpApp.Application;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@AutoConfigureMockMvc
public class EmployeeTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void checkNameHasNoNumbers() throws Exception {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"344444\",\n" +
                "\t\"email\":\"fsqSp@yl.com\",\n" +
                "\t\"day\":5,\n" +
                "\t\"month\":5,\n" +
                "\t\"year\":1997,\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }

    @Test
    public void EmptyName() throws Exception {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"\",\n" +
                "\t\"email\":\"fsqSp@yl.com\",\n" +
                "\t\"day\":5,\n" +
                "\t\"month\":5,\n" +
                "\t\"year\":1997,\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }


    @Test
    public void checkEmailSyntax() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"ssadokasdkoda\",\n" + //Email without "@"
                "\t\"day\":5,\n" +
                "\t\"month\":5,\n" +
                "\t\"year\":1997,\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }

    @Test
    public void CheckInvalidAge() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"ahmed@live.com\",\n" + //Email without "@"
                "\t\"day\":5,\n" +
                "\t\"month\":5,\n" +
                "\t\"year\":2010,\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }


    @Test
    public void CheckInvalidDepWithTitle() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"ssadokasdkoda\",\n" + //Email without "@"
                "\t\"day\":5,\n" +
                "\t\"month\":5,\n" +
                "\t\"year\":2010,\n" +
                "\t\"title_name\":\"HR Manager\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }


    @Test
    public void CheckDepExists() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"ssadokasdkoda@djs.com\",\n" + //Email without "@"
                "\t\"day\":5,\n" +
                "\t\"month\":5,\n" +
                "\t\"year\":1997,\n" +
                "\t\"title_name\":\"HR Manager\",\n" +
                "\t\"department_name\":\"Mohamed\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }

    @Test
    public void CheckTitleExists() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"ssadokasdkoda@djs.com\",\n" + //Email without "@"
                "\t\"day\":5,\n" +
                "\t\"month\":5,\n" +
                "\t\"year\":1997,\n" +
                "\t\"title_name\":\"Ahmed\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }



    @Test
    public void CheckMonthOutOfRange() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"dkoda@dsda.com\",\n" + //Email without "@"
                "\t\"day\":5,\n" +
                "\t\"month\":52,\n" +
                "\t\"year\":2000,\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }

    @Test
    public void CheckDayOutOfRange() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"dkoda@dsda.com\",\n" + //Email without "@"
                "\t\"day\":33,\n" +
                "\t\"month\":3,\n" +
                "\t\"year\":2000,\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }

    @Test
    public void CheckDayDataType() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"dkoda@dsda.com\",\n" + //Email without "@"
                "\t\"day\":\"ahmed\",\n" +
                "\t\"month\":3,\n" +
                "\t\"year\":2000,\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }

    @Test
    public void CheckMonthSyntaxDataType() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"dkoda@dsda.com\",\n" + //Email without "@"
                "\t\"day\":5,\n" +
                "\t\"month\":\"ahmed\",\n" +
                "\t\"year\":2000,\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }

    @Test
    public void CheckYearSyntaxDataType() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"dkoda@dsda.com\",\n" + //Email without "@"
                "\t\"day\":5,\n" +
                "\t\"month\":10,\n" +
                "\t\"year\":\"ahmed\",\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isBadRequest());

    }


    @Test
    public void Good() throws Exception
    {
        String body = "\n" +
                "{\n" +
                "\t\"username\": \"Ahmed\",\n" +
                "\t\"email\":\"ssadokasdkoda@dsda.com\",\n" + //Email without "@"
                "\t\"day\":5,\n" +
                "\t\"month\":5,\n" +
                "\t\"year\":2000,\n" +
                "\t\"title_name\":\"Software Developer\",\n" +
                "\t\"department_name\":\"Development\"\n" +
                "}";

        mockMvc.perform(post("/Employee/Add")
                .header("content-type", "application/json").content(body)).andExpect(status().isOk());

    }




}
