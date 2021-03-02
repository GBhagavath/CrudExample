package com.example.practice.dba;

import com.example.practice.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
    Employee findById(int id);
    boolean deleteById(int id);
}
