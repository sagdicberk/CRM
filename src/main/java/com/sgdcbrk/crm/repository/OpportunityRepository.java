package com.sgdcbrk.crm.repository;

import com.sgdcbrk.crm.model.opportunity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
}
