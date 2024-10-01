package com.sgdcbrk.crm.business.concretes;

import com.sgdcbrk.crm.business.abstracts.OpportunityService;
import com.sgdcbrk.crm.model.opportunity.Opportunity;
import com.sgdcbrk.crm.model.opportunity.OpportunityStatus;
import com.sgdcbrk.crm.repository.OpportunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpportunityManager implements OpportunityService {
    private final OpportunityRepository opportunityRepository;

    @Override
    public void saveOpportunity(Opportunity opportunity) {
        opportunity.setStatus(OpportunityStatus.OPEN);
        opportunityRepository.save(opportunity);
    }

    @Override
    public void updateOpportunity(long id, Opportunity opportunity) {
        Opportunity existingOpportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No opportunity found with id " + id));
        existingOpportunity.setName(opportunity.getName());
        existingOpportunity.setValue(opportunity.getValue());
        existingOpportunity.setExpectedCloseDate(opportunity.getExpectedCloseDate());

        opportunityRepository.save(existingOpportunity);
    }

    @Override
    public void deleteOpportunityById(long id) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No opportunity found with id " + id));
        opportunityRepository.delete(opportunity);
    }

    @Override
    public void completeOpportunityWithWon(Opportunity opportunity) {
        opportunity.setStatus(OpportunityStatus.WON);
        opportunityRepository.save(opportunity);
    }

    @Override
    public void completeOpportunityWithLost(Opportunity opportunity) {
        opportunity.setStatus(OpportunityStatus.LOST);
        opportunityRepository.save(opportunity);
    }

    @Override
    public Opportunity getOpportunity(long id) {
        return opportunityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No opportunity found with id " + id));
    }

    @Override
    public List<Opportunity> getOpportunities() {
        return opportunityRepository.findAll();
    }
}