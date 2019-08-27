package com.pixelogic.EmpApp.Controller;

import com.pixelogic.EmpApp.Entity.Department;
import com.pixelogic.EmpApp.Entity.Employee;
import com.pixelogic.EmpApp.Repository.DepartmentRepository;
import com.pixelogic.EmpApp.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(value="/AddDep",method= RequestMethod.POST)
    public void AddDep( @RequestBody Map<String,Object> maps)
    {
        Department department = new Department();
        department.setDepartment_name(maps.get("Department_Name").toString());
       departmentRepository.save(department);
    }



    @RequestMapping(value="/ViewAllDeps", method= RequestMethod.GET)
    public List<Department> ViewDepartments( )
    {
        return departmentRepository.findAll();
    }


    @RequestMapping(value="/DepHasLeaderOrNot" , method = RequestMethod.GET)
    public Long  DepHasLeaderOrNot ( @RequestParam(value = "DepName") String DepName)
    {
       Department department  =  departmentRepository.findDepbyDepname(DepName);
       Long Leader_ID = Long.valueOf(0);

       if(department.getLeader_id() != null)
        Leader_ID = department.getLeader_id();

       return Leader_ID;
    }


    @RequestMapping(value = "/AssignLeaderToDep",method = RequestMethod.PUT)
     public void AssignLeaderToDep( @RequestBody Map<String,Object> maps)
    {
        String email = (String)maps.get("email");
       Employee employee = employeeRepository.findEmployeeByEmail(email);
       //if(employee !=null)
        //       //{
        System.out.println("Email"+ email);
        System.out.println("Employee" + employee.getUsername());
            Department department = employee.getTitle().getDepartment();
            department.setLeader_id(employee.getId());

            departmentRepository.save(department);

      // }
    }
}
