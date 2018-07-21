package com.example.app.repository;

import com.example.app.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFullnameIgnoreCaseContaining(String fullname);

    List<Employee> findByPassportIgnoreCaseContaining(String passport);

    List<Employee> findByPhoneIgnoreCaseContaining(String phone);

    @Query(value = "SELECT * FROM t_employee WHERE passport = ?1", nativeQuery = true)
    Employee findByPassport(String passport);

}
