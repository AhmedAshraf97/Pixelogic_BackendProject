package com.pixelogic.EmpApp.Repository;

import com.pixelogic.EmpApp.Entity.Department;
import com.pixelogic.EmpApp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EmployeeRepository extends JpaRepository<Employee,Long> {
//
//    @Query(value = "select * from employee where department_department_id = ?1 AND title_title_id = ?2 ",nativeQuery = true)
//    Employee DepHasOneLeaderOrNot(Long Dep_Id,Long Title_Id);
//
//    @Query(value = "Select * from employee where username = ?1 AND title_title_id ",nativeQuery = true)
//    Employee findLeaderIdbyName(String LeaderName,Long Title_Id);

    @Query(value = "Select count(*) from employee where email = ?1 ",nativeQuery = true)
    int CheckEmailUsed(String Email);

    @Query(value="Select * from employee where email = ?1",nativeQuery = true)
    Employee findEmployeeByEmail(String Email);

   // @Query(value=" Select * from employee,Departments,Titles where Title_name = 'Leader' And Department_name = ?1")
  //  List<Employee> GetLeaderOnly;
}
