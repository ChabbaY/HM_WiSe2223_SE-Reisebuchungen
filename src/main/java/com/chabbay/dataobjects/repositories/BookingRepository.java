package com.chabbay.dataobjects.repositories;

import com.chabbay.dataobjects.objects.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * interface to database (here in-memory)
 *
 * @author Linus Englert
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}