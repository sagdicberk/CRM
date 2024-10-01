package com.sgdcbrk.crm.controller;

import com.sgdcbrk.crm.business.abstracts.CustomerService;
import com.sgdcbrk.crm.model.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        try {
            customerService.addCustomer(customer);
            return ResponseEntity.status(201).body("Customer created");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating customer: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        try {
            customerService.updateCustomer(id, customer);
            return ResponseEntity.ok("Customer updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating customer: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
        try {
            customerService.deleteCustomerById(id);
            return ResponseEntity.ok("Customer deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting customer: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable long id) {
        try {
            Customer customer = customerService.findCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error finding customer: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> findCustomerByEmail(@RequestParam String email) {
        try {
            Customer customer = customerService.findCustomerByEmail(email);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error finding customer by email: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllCustomers() {
        try {
            List<Customer> customers = customerService.findAllCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error finding all customers: " + e.getMessage());
        }
    }
}
