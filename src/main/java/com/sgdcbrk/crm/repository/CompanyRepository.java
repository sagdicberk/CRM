package com.sgdcbrk.crm.repository;

import com.sgdcbrk.crm.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByName(String companyName);
}
