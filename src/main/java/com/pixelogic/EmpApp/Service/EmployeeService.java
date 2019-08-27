package com.pixelogic.EmpApp.Service;

import com.pixelogic.EmpApp.CustomException;
import com.pixelogic.EmpApp.Entity.Department;
import com.pixelogic.EmpApp.Entity.Employee;
import com.pixelogic.EmpApp.Entity.Title;
import com.pixelogic.EmpApp.Repository.DepartmentRepository;
import com.pixelogic.EmpApp.Repository.EmployeeRepository;
import com.pixelogic.EmpApp.Repository.TitleRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TitleRepository titleRepository;

    public void CalcAge(Employee employee) {

        if(employee.getBirthdate() != null) {
            int year = employee.getBirthdate().getYear();
            int today = LocalDate.now().getYear();

            Period p = Period.between(employee.getBirthdate(), LocalDate.now());
            employee.setAge(p.getYears());
        }
    }

    private boolean CheckTitleExists(Employee employee)
    {
        Title title = employee.getTitle();

        if(title == null)
            return false;

        return true;
    }

    private boolean CheckDepExists(Employee employee, String DepName)
    {
       Department department= departmentRepository.findDepbyDepname(DepName);
       if(department == null)
           return false;

       return true;
    }

    private boolean CheckTitleInDepartment(Employee employee,String DepName){

        Title title = employee.getTitle();
        Department department;
       if(title != null) {
           department = title.getDepartment();
           System.out.println(department.getDepartment_name());

           if( department== null || !department.getDepartment_name().equalsIgnoreCase(DepName))
               return false;
           return true;

       }
       return false;
       }


    private boolean CheckUsedEmail(Employee employee)
    {
        if( employee.getEmail() != null) {
            int EmailCount = employeeRepository.CheckEmailUsed(employee.getEmail());
            if (EmailCount == 0) return true;
            return false;
        }
        return  false;
    }


    public boolean CheckDate(int day, int month,int year )
    {

        if(year > (Year.now().getValue() - 19) || (year < (Year.now().getValue() - 100 )) || month <= 0 || month >12 || day <= 0 || day >31 )
            return false;
        return  true;
    }


    public boolean CheckUsername( String Username)
    {
        if(Username != null ) {
            if ( (Username.length() > 20) || !Pattern.matches("^[a-zA-Z]+[ a-zA-Z]*$", Username))
                return false;

            return true;
        }
        return  false;
    }



    public boolean CheckEmail ( String  Email )
    {

        if(Email != null) {
            if (!Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Email))
                return false;

            return true;
        }

        return false;
    }





    public String Check(Employee employee, String DepName) throws CustomException {

           if(!CheckUsedEmail(employee))
           {
               throw new CustomException("Sorry, Please choose another Email ") ;
           }
           else if (! CheckTitleExists(employee))
           {
               throw new CustomException(" Title Doesn't exist");
           }
           else if( !CheckDepExists(employee,DepName))
           {
               throw new CustomException(" Please check valid department, this department doesn't exist");
           }
           else if ( !CheckEmail(employee.getEmail()))
           {

               throw new CustomException(  " Invalid Email Syntax or Empty");
           }
           else if ( !CheckUsername(employee.getUsername()))
           {
               throw new CustomException(  " Username contains invalid characters or Empty");
           }
           else if( !CheckTitleInDepartment(employee,DepName))
           {
               throw new CustomException(" Invalid title with dep ");
           }

        return "Success";
    }



}
