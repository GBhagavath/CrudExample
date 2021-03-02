package com.example.practice.controller;

import com.example.practice.common.APIResponseBuilder;
import com.example.practice.common.APISuccessCodes;
import com.example.practice.model.Company;
import com.example.practice.model.Employee;
import com.example.practice.service.CompnayService;
import com.example.practice.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompnayService compnayService;
    private final APIResponseBuilder apiResponseBuilder;

    public EmployeeController(EmployeeService employeeService, CompnayService compnayService, APIResponseBuilder apiResponseBuilder) {
        this.employeeService = employeeService;
        this.compnayService = compnayService;
        this.apiResponseBuilder = apiResponseBuilder;
    }

    @RequestMapping("/add")
    public Map<String, Object> addUser(@RequestBody Map<String, String> user) {
        Map<String, Object> responseMap;
        Company company = compnayService.findCompany(Integer.parseInt(user.get("companyId")));
        Employee emp = new Employee();
        emp.setCompany(company);
        emp.setFirstName(user.get("firstName"));
        emp.setLastName(user.get("lastName"));
        emp.setSalary(Float.parseFloat(user.get("salary")));
        employeeService.save(emp);
        String responseCode = APISuccessCodes.USER_ADDED;
        responseMap = apiResponseBuilder.buildResponse(responseCode, "user added successfully");
        return responseMap;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable("id") int id) {
        Map<String, Object> responseMap = null;
        try {
            CompletableFuture<Employee> user = employeeService.get(id);
            String responseCode = APISuccessCodes.USER_FETCHED;
            responseMap = apiResponseBuilder.buildResponse(String.valueOf(id), user.get(), responseCode);
            return responseMap;
        } catch (Exception e) {
            throw new RuntimeException("Runtime error in UserController: get() : ", e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public Map<String, Object> deleteUser(@PathVariable("id") int id) {

        Map<String, Object> responseMap;
        try {
            boolean isDeleted = employeeService.delete(id);
            String responseMessage = isDeleted ? "Deleted the User" : "Faced a problem";
            responseMap = apiResponseBuilder.buildResponse(APISuccessCodes.USER_DELETED, responseMessage);
            return responseMap;
        } catch (Exception e) {
            throw new RuntimeException("Runtime error in UserController: get() : ", e);
        }
    }
}
