package com.example.practice.service;

import com.example.practice.dba.CompanyRepository;
import com.example.practice.dba.EmployeeRepository;
import com.example.practice.model.Employee;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public Employee save(Employee emp) {
        return employeeRepository.save(emp);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Employee> get(int id) throws InterruptedException {
        return CompletableFuture.completedFuture(employeeRepository.findById(id));
    }

    public boolean delete(int id) {
        return employeeRepository.deleteById(id);
    }
}
