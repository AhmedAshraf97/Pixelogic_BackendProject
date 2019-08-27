package com.pixelogic.EmpApp;

import com.pixelogic.EmpApp.Entity.Department;
import com.pixelogic.EmpApp.Entity.Title;
import com.pixelogic.EmpApp.Repository.DepartmentRepository;
import com.pixelogic.EmpApp.Repository.TitleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner seedDeps(
           DepartmentRepository departmentRepository,
            TitleRepository titleRepository
    )
    {

        return(args) ->
        {
            String [] Deps = {"Development", "Production", "Human Resources"};

            for(String dep : Deps)
            {
                Department department = new Department();
                department.setDepartment_name(dep);
                departmentRepository.save(department);
            }

            Title title = new Title();

            title.setTitle_name("Software Developer");
            Department department = departmentRepository.findDepbyDepname("Development");
            title.setDepartment(department);
            titleRepository.save(title);


            Title title2 = new Title();

            title2.setTitle_name("Software Tester");
            title2.setDepartment(department);
            titleRepository.save(title2);

            Title title3 = new Title();

            title3.setTitle_name("QC Subtitles Tester");
            title3.setDepartment(department);
            titleRepository.save(title3);


            Title title4 = new Title();
            title4.setTitle_name("Translator");
            department = departmentRepository.findDepbyDepname("Production");
            title4.setDepartment(department);
            titleRepository.save(title4);

            Title title5 = new Title();

            department = departmentRepository.findDepbyDepname("Human Resources");
            title5.setDepartment(department);
            title5.setTitle_name("HR Employee");
            titleRepository.save(title5);

            Title title6 = new Title();
            title6.setDepartment(department);
            title6.setTitle_name("HR Manager");
            titleRepository.save(title6);
        };
    }
}
