package com.example.app.service.impl;

import com.example.app.domain.Employee;
import com.example.app.repository.EmployeeRepository;
import com.example.app.service.EmployeeService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void createNewEmployee(@NonNull Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByFullname(@NotEmpty String name) {
        return ListUtils.emptyIfNull(employeeRepository.findByFullnameIgnoreCaseContaining(name));
    }

    @Override
    public List<Employee> getEmployeesByPhone(String phone) {
        return ListUtils.emptyIfNull(employeeRepository.findByPhoneIgnoreCaseContaining(phone));
    }

    @Override
    public List<Employee> getEmployeesByPassportNumber(String passport) {
        return ListUtils.emptyIfNull(employeeRepository.findByPassportIgnoreCaseContaining(passport));
    }

    @Override
    public Employee getByPassport(String passport) {
        return employeeRepository.findByPassport(passport);
    }
}
