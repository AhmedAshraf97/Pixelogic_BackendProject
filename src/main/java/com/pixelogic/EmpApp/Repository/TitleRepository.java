package com.pixelogic.EmpApp.Repository;

import com.pixelogic.EmpApp.Entity.Department;
import com.pixelogic.EmpApp.Entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TitleRepository  extends JpaRepository<Title,Long> {

    @Query(value = "Select * from Titles where Title_name = ?1 ",nativeQuery = true)
    Title findTitlebyTitleName(String TitleName);

    @Query(nativeQuery = true , value = "select * from Titles where department_department_Id = ( Select department_id from departments where department_name = ?1 )")
    List<Title> findTitlesByDepartmentName(String DepName);

    @Query(value = "Select * from Titles where department_department_Id = ?1",nativeQuery = true)
    List<Title> findTitleIdByDepID(Long DepID);

    @Query(value = "Select * from Titles where Title_name = Leader",nativeQuery = true)
    Title ListAllLeaders();


    @Query(value = "Select * from Titles where Title_name = 'Leader' And department_department_id = ?1",nativeQuery = true)
    Title GetDepLeadertitleId(Long DepId);

}
