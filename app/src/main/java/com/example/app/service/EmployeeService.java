package com.example.app.service;

import com.example.app.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    void createNewEmployee(Employee employee);

    List<Employee> getEmployeesByFullname(String name);

    List<Employee> getEmployeesByPhone(String phone);

    List<Employee> getEmployeesByPassportNumber(String passport);

}
