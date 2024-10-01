package com.sgdcbrk.crm.controller;

import com.sgdcbrk.crm.business.abstracts.OpportunityService;
import com.sgdcbrk.crm.model.opportunity.Opportunity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
@AllArgsConstructor
public class OpportunityController {
    private final OpportunityService opportunityService;

    @PostMapping
    public ResponseEntity<String> createOpportunity(@RequestBody Opportunity opportunity) {
        try {
            opportunityService.saveOpportunity(opportunity);
            return ResponseEntity.ok("Opportunity created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating opportunity: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOpportunity(@PathVariable long id, @RequestBody Opportunity opportunity) {
        try {
            opportunityService.updateOpportunity(id, opportunity);
            return ResponseEntity.ok("Opportunity updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating opportunity: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOpportunity(@PathVariable long id) {
        try {
            opportunityService.deleteOpportunityById(id);
            return ResponseEntity.ok("Opportunity deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting opportunity: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Opportunity> getOpportunity(@PathVariable long id) {
        try {
            Opportunity opportunity = opportunityService.getOpportunity(id);
            return ResponseEntity.ok(opportunity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Opportunity>> getOpportunities() {
        try {
            List<Opportunity> opportunities = opportunityService.getOpportunities();
            return ResponseEntity.ok(opportunities);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{id}/won")
    public ResponseEntity<String> completeOpportunityWithWon(@PathVariable long id) {
        try {
            Opportunity opportunity = opportunityService.getOpportunity(id);
            opportunityService.completeOpportunityWithWon(opportunity);
            return ResponseEntity.ok("Opportunity marked as WON");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error marking opportunity as WON: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/lost")
    public ResponseEntity<String> completeOpportunityWithLost(@PathVariable long id) {
        try {
            Opportunity opportunity = opportunityService.getOpportunity(id);
            opportunityService.completeOpportunityWithLost(opportunity);
            return ResponseEntity.ok("Opportunity marked as LOST");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error marking opportunity as LOST: " + e.getMessage());
        }
    }
}









