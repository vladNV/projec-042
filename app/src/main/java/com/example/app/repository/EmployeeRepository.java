package com.example.app.repository;

import com.example.app.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFullnameIgnoreCaseContaining(String fullname);

//    @Query("SELECT e FROM Employee e WHERE e.passport LIKE %:passport%")
    List<Employee> findByPassportIgnoreCaseContaining(String passport);

//    @Query("SELECT e FROM Employee e WHERE e.phone LIKE %:fullname%")
    List<Employee> findByPhoneIgnoreCaseContaining(String phone);
}
