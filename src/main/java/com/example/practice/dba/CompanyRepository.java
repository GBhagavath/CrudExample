package com.example.practice.dba;

import com.example.practice.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Company findById(int id);
}
