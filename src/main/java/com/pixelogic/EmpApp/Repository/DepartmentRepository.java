package com.pixelogic.EmpApp.Repository;

import com.pixelogic.EmpApp.Entity.Department;
import com.pixelogic.EmpApp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query(value = "Select * from Departments where Department_name = ?1 ",nativeQuery = true)
    Department findDepbyDepname(String Depname);




}
