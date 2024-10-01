package com.sgdcbrk.crm.business.abstracts;

import com.sgdcbrk.crm.model.opportunity.Opportunity;

import java.util.List;

public interface OpportunityService {
    void saveOpportunity(Opportunity opportunity);
    void updateOpportunity(long id, Opportunity opportunity);
    void deleteOpportunityById(long id);
    void completeOpportunityWithWon(Opportunity opportunity);
    void completeOpportunityWithLost(Opportunity opportunity);


    Opportunity getOpportunity(long id);
    List<Opportunity> getOpportunities();

}
