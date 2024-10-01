package com.sgdcbrk.crm.business.concretes;

import com.sgdcbrk.crm.business.abstracts.CustomerService;
import com.sgdcbrk.crm.model.customer.Customer;
import com.sgdcbrk.crm.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public void addCustomer(Customer customer) {
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Error creating customer: " + e.getMessage());
        }
    }

    @Override
    public void updateCustomer(long id, Customer customer) {
        try {
            Customer existingCustomer = customerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
            existingCustomer.setName(customer.getName());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setCompany(customer.getCompany());
            customerRepository.save(existingCustomer);
        } catch (Exception e) {
            throw new RuntimeException("Error updating customer: " + e.getMessage());
        }
    }

    @Override
    public void deleteCustomerById(long id) {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting customer: " + e.getMessage());
        }
    }

    @Override
    public Customer findCustomerById(long id) {
        try {
            return customerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error finding customer: " + e.getMessage());
        }
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        try {
            return customerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Customer not found with email: " + email));
        } catch (Exception e) {
            throw new RuntimeException("Error finding customer by email: " + e.getMessage());
        }
    }

    @Override
    public List<Customer> findAllCustomers() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error finding all customers: " + e.getMessage());
        }
    }

}
