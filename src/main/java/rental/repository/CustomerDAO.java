package rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rental.entity.Customer;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    List<Customer> findAll();

    Customer findById(Long id);
}
