package rental.service;

import java.util.List;

import rental.entity.Customer;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);
}
