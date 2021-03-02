package com.example.practice.service;

import com.example.practice.dba.CompanyRepository;
import com.example.practice.model.Company;
import org.springframework.stereotype.Service;

@Service
public class CompnayService {
    private CompanyRepository companyRepository;

    public CompnayService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company findCompany(int companyId) {
        return companyRepository.findById(companyId);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }
}
