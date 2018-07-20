package com.example.app.controller;

import com.example.app.converter.EmployeeConverter;
import com.example.app.model.EmployeeModel;
import com.example.app.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    @ResponseBody
    public List<EmployeeModel> getAllEmployees() {
        return null;
    }

    @GetMapping("/search/name/{like}")
    @ResponseBody
    public ResponseEntity<List<EmployeeModel>> searchEmployeesByName(@PathVariable("like") String like) {
        if (StringUtils.isBlank(like)) {
            return Result.response(HttpStatus.BAD_REQUEST);
        }
        return Result.response(employeeService.getEmployeesByFullname(like)
                .stream().map(EmployeeConverter::toModel)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/search/phone/{like}")
    @ResponseBody
    public ResponseEntity<List<EmployeeModel>> searchEmployeesByPhone(@PathVariable("like") String like) {
        if (StringUtils.isBlank(like)) {
            return Result.response(HttpStatus.BAD_REQUEST);
        }
        return Result.response(employeeService.getEmployeesByPhone(like)
                .stream().map(EmployeeConverter::toModel)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/search/passport/{like}")
    @ResponseBody
    public ResponseEntity<List<EmployeeModel>> searchEmployeesByPassport(@PathVariable("like") String like) {
        if (StringUtils.isBlank(like)) {
            return Result.response(HttpStatus.BAD_REQUEST);
        }
        return Result.response(employeeService.getEmployeesByPassportNumber(like)
                .stream().map(EmployeeConverter::toModel)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
