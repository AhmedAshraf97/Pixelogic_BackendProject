package UnitTests;

import com.pixelogic.EmpApp.Entity.Employee;
import com.pixelogic.EmpApp.Repository.DepartmentRepository;
import com.pixelogic.EmpApp.Repository.EmployeeRepository;
import com.pixelogic.EmpApp.Repository.TitleRepository;
import com.pixelogic.EmpApp.Service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmployeeService.class})

public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private  TitleRepository titleRepository;



    @Test
    public void TestCalculateAge()
    {
        LocalDate birthdate = LocalDate.parse("1995-11-22");
        LocalDate year = LocalDate.parse("2018-11-20");

        Employee employee = new Employee();

        employee.setBirthdate(20,5,1997);
        employeeService.CalcAge(employee);
        int age = employee.getAge();

        assertEquals(age,22);
    }



    @Test
    public void TestDate() {
        int year = 1998;
        int month = 13;
        int day = 33;

        boolean x = employeeService.CheckDate(day, month, year);

        assertEquals(x, false);
    }

    @Test
    public void TestDate2() {
        int year = 19982222;
        int month = 99;
        int day = 33;

        boolean x = employeeService.CheckDate(day, month, year);

        assertEquals(x, false);
    }

    @Test
    public void TestUsername()
    {
        String name = "Ahmedddd22222";
       boolean x =  employeeService.CheckUsername(name);
       assertEquals(x,false);
    }


    @Test
    public void TestUsername2()
    {
        String name = "";
        boolean x = employeeService.CheckUsername(name);
        assertEquals(x,false);
    }

    @Test
    public void TestUsername3()
    {
        String name = "22222";
        boolean x =  employeeService.CheckUsername(name);
        assertEquals(x,false);
    }

    @Test
    public void TestEmail()
    {
        String email = "ahmed";
        boolean x = employeeService.CheckEmail(email);
        assertEquals(x,false);
    }

    @Test
    public void TestEmail2()
    {
        String email = "ahmed@@@";
        boolean x = employeeService.CheckEmail(email);
        assertEquals(x,false);
    }


}
