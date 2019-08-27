package com.pixelogic.EmpApp.Controller;

import com.pixelogic.EmpApp.Entity.Department;
import com.pixelogic.EmpApp.Entity.Employee;
import com.pixelogic.EmpApp.Entity.Title;
import com.pixelogic.EmpApp.CustomException;
import com.pixelogic.EmpApp.Repository.DepartmentRepository;
import com.pixelogic.EmpApp.Repository.EmployeeRepository;
import com.pixelogic.EmpApp.Repository.TitleRepository;
import com.pixelogic.EmpApp.Service.EmployeeService;
import javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.InternalParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository ;

      @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TitleRepository titleRepository;



    @ResponseBody
    @ExceptionHandler(value= CustomException.class)
    public ResponseEntity HandleException(CustomException e){
        HttpHeaders h =new HttpHeaders();
        HashMap<String,String> map=new HashMap<>();
        map.put("Body",e.getError());
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value="/Add", method= RequestMethod.POST)
    public void AddUser( @RequestBody Map<String,Object> maps) throws CustomException {
        Employee employee = new Employee();
        employee.setUsername(maps.get("username").toString());
        Title title = titleRepository.findTitlebyTitleName(maps.get("title_name").toString());
        employee.setTitle(title);
       // Department department = departmentRepository.findDepbyDepname(maps.get("department_name").toString());
        String DepName = maps.get("department_name").toString();
        //employee.getTitle().setDepartment(department);
        employee.setEmail(maps.get("email").toString());

      /////////////////////////////////////////////////////////////////////////////

       String DayType =  maps.get("day").getClass().getName();
       String MonthType =  maps.get("month").getClass().getName();
       String YearType =  maps.get("year").getClass().getName();
       boolean DateValidate = false;

       if( DayType == "java.lang.Integer" && MonthType == "java.lang.Integer" && YearType == "java.lang.Integer" ) {
           if (employeeService.CheckDate(Integer.parseInt(maps.get("day").toString()), Integer.parseInt(maps.get("month").toString()), Integer.parseInt(maps.get("year").toString())))
           {

               employee.setBirthdate(Integer.parseInt(maps.get("day").toString()), Integer.parseInt(maps.get("month").toString()), Integer.parseInt(maps.get("year").toString()));
               DateValidate = true;

           }
           else
               throw new CustomException("Invalid date range");
       }
       else  throw new CustomException("Invalid date type");

       ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        employeeService.CalcAge(employee);
        String check=employeeService.Check(employee,DepName);
        if (check =="Success" && DateValidate ){
            employeeRepository.save(employee);
        }
       else System.out.println(check);
    }


    @RequestMapping(value="/ViewEmployees", method= RequestMethod.GET)
    public List<Employee> ViewEmployee( )
    {
        return employeeRepository.findAll();
    }

}
