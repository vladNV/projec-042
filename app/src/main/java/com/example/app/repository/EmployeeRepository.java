package com.example.app.repository;

import com.example.app.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(name = "SELECT * FROM t_employee WHERE fullname LIKE %?1", nativeQuery = true)
    List<Employee> findByFullname(String fullname);

    @Query(name = "SELECT * FROM t_employee WHERE passport LIKE %?1", nativeQuery = true)
    List<Employee> findByPassport(String passport);

    @Query(name = "SELECT * FROM t_employee WHERE phone LIKE %?1", nativeQuery = true)
    List<Employee> findByPhone(String phone);
}
