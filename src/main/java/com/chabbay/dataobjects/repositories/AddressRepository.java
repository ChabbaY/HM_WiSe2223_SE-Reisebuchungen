package com.chabbay.dataobjects.repositories;

import com.chabbay.dataobjects.objects.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * interface to database (here in-memory)
 *
 * @author Linus Englert
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByAddressInformationId(Long addressInformationId);
}