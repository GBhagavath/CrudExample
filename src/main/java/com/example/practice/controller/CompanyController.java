package com.example.practice.controller;

import com.example.practice.common.APIResponseBuilder;
import com.example.practice.common.APISuccessCodes;
import com.example.practice.model.Company;
import com.example.practice.service.CompnayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CompanyController {

    private final CompnayService compnayService;
    private final APIResponseBuilder apiResponseBuilder;

    public CompanyController(CompnayService compnayService, APIResponseBuilder apiResponseBuilder) {
        this.compnayService = compnayService;
        this.apiResponseBuilder = apiResponseBuilder;
    }

    @PostMapping("/addcompany")
    public Map<String, Object> addCompany(@RequestBody Map<String, String> company) {
        Company comp = new Company();
        comp.setName(company.get("name"));
        comp.setAddress(company.get("address"));
        compnayService.save(comp);
        String responseCode = APISuccessCodes.COMPANY_ADDED;
        Map<String, Object> responseMap = apiResponseBuilder.buildResponse(responseCode, "user added successfully");
        return responseMap;
    }
}
