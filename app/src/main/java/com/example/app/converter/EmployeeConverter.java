package com.example.app.converter;

import com.example.app.domain.Employee;
import com.example.app.model.EmployeeModel;
import org.springframework.lang.NonNull;

/**
 * Class Converter
 */
public class EmployeeConverter {

    public static EmployeeModel toModel(@NonNull Employee employee) {
        EmployeeModel employeeModel = new EmployeeModel();

        employeeModel.setFullname(employee.getFullname());
        employeeModel.setId(employee.getId());
        employeeModel.setPassport(employee.getPassport());
        employeeModel.setPhone(employee.getPhone());
        employeeModel.setQualification(employee.getQualification());
        employeeModel.setPosition(employee.getPosition().name());

        return employeeModel;
    }

}
