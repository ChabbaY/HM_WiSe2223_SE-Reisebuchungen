package com.chabbay.dataobjects.repositories;

import com.chabbay.dataobjects.objects.CountryTimezone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to database (here in-memory)
 *
 * @author Linus Englert
 */
@Repository
public interface CountryTimezoneRepository extends JpaRepository<CountryTimezone, Long> {
    List<CountryTimezone> findByCountryId(long countryId);
    List<CountryTimezone> findByTimezoneId(long timezoneId);
}