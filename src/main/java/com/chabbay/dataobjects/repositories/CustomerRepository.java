package com.chabbay.dataobjects.repositories;

import com.chabbay.dataobjects.objects.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to database (here in-memory)
 *
 * @author Linus Englert
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByAddressInformationId(long addressInformationId);
}