package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
    private Long id;
    private String fullname;
    private String position;
    private String phone;
    private String qualification;
    private String passport;


}
