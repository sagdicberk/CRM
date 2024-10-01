package com.sgdcbrk.crm.business.abstracts;

import com.sgdcbrk.crm.model.customer.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);
    void updateCustomer(long id, Customer customer);
    void deleteCustomerById(long id);
    Customer findCustomerById(long id);
    Customer findCustomerByEmail(String email);
    List<Customer> findAllCustomers();
}
