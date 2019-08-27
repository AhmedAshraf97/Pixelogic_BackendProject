package com.pixelogic.EmpApp.Controller;

import com.pixelogic.EmpApp.Entity.Department;
import com.pixelogic.EmpApp.Entity.Title;
import com.pixelogic.EmpApp.Repository.DepartmentRepository;
import com.pixelogic.EmpApp.Repository.EmployeeRepository;
import com.pixelogic.EmpApp.Repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TitleController {
    @Autowired
    TitleRepository titleRepository;

    @Autowired
    DepartmentRepository departmentRepository ;

    @Autowired
    EmployeeRepository employeeRepository;


    @RequestMapping(value="/AddTitle",method= RequestMethod.POST)
    public void AddTitle( @RequestBody Map<String,Object> maps)
    {
        Title title = new Title();
        title.setTitle_name(maps.get("Title_name").toString());
        Department department = departmentRepository.findDepbyDepname(maps.get("department_name").toString());
        title.setDepartment(department);
        titleRepository.save(title);
    }

    @RequestMapping(value="/GetTitlesbyDepName" , method = RequestMethod.GET)
    public List<Title> GetTitlebyDepName(@RequestParam (value = "DepName") String DepName)
    {
       List<Title> title =  titleRepository.findTitleIdByDepID(departmentRepository.findDepbyDepname(DepName).getDepartment_id());

       for(int i = 0 ; i< title.size(); i++)
       {
          System.out.println(title.get(i).getTitle_name());        }
        return title;
    }

    @RequestMapping(value="/GetDepLeaderTitleId",method = RequestMethod.GET)
    public Title GetDepLeadertitleId(@RequestParam (value = "DepName") String DepName)
    {
        Title title_Id = titleRepository.GetDepLeadertitleId(departmentRepository.findDepbyDepname(DepName).getDepartment_id());
        return title_Id;
    }

}
