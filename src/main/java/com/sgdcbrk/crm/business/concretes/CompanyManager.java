package com.sgdcbrk.crm.business.concretes;

import com.sgdcbrk.crm.business.abstracts.CompanyService;
import com.sgdcbrk.crm.model.company.Company;
import com.sgdcbrk.crm.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyManager implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(long id ,Company company) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id " + company.getId()));
        existingCompany.setEmail(company.getEmail());
        existingCompany.setAddress(company.getAddress());
        existingCompany.setPhone(company.getPhone());
        existingCompany.setIndustry(company.getIndustry());
        existingCompany.setName(company.getName());
        companyRepository.save(existingCompany);
    }

    @Override
    public void deleteCompanyById(long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company findCompanyById(long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No company found with id " + id));
    }

    @Override
    public Company findCompanyByName(String name) {
        return companyRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("No company found with name " + name));
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }
}