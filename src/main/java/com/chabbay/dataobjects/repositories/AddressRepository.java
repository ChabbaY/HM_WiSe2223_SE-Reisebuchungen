package com.chabbay.dataobjects.repositories;

import com.chabbay.dataobjects.objects.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to database (here in-memory)
 *
 * @author Linus Englert
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByAddressInformationId(long addressInformationId);
    List<Address> findByCountryId(long countryId);
    List<Address> findByTimezoneId(long timezoneId);
}