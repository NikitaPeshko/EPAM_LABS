package com.epam.esc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.epam.esc.dao.EmployeeDAO;
import com.epam.esc.model.Employee;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    private EmployeeDAO employeeDAO;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }

    // URL:
    // http://localhost:8080/SpringMVCRESTful/employees
    // http://localhost:8080/SpringMVCRESTful/employees.xml
    // http://localhost:8080/SpringMVCRESTful/employees.json
    @RequestMapping(value = "/employees", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }

    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}.xml
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}.json
    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") String empNo) {
        return employeeDAO.getEmployee(empNo);
    }

    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee
    // http://localhost:8080/SpringMVCRESTful/employee.xml
    // http://localhost:8080/SpringMVCRESTful/employee.json
    @RequestMapping(value = "/employee", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {

        return employeeDAO.addEmployee(emp);

    }

    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee
    // http://localhost:8080/SpringMVCRESTful/employee.xml
    // http://localhost:8080/SpringMVCRESTful/employee.json
    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {

        return employeeDAO.updateEmployee(emp);
    }



    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}
    @RequestMapping(value = "/employees/{empNo}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo) {
        employeeDAO.deleteEmployee(empNo);
    }
}
