package com.chabbay.main;

import com.chabbay.dataobjects.objects.*;
import com.chabbay.dataobjects.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * initialization of data base values
 *
 * @author Linus Englert
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.chabbay"})
public class LoadDatabase {
    /**
     * referencing the logger
     */
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initAddress(AddressRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Address(
                    "Hauptstraße",
                    "1",
                    "88888",
                    "Musterstadt",
                    null,
                    1L,
                    0,
                    0)));
        };
    }
    @Bean
    CommandLineRunner initAddressInformation(AddressInformationRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new AddressInformation(
                    "Herr",
                    "098 1234567",
                    "abc")));
        };
    }
    @Bean
    CommandLineRunner initCountry(CountryRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Country(
                    "Deutschland",
                    "Deutsch",
                    "DE")));
        };
    }
    @Bean
    CommandLineRunner initDatabase(HotelRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Hotel(
                    "Hotel 1",
                    1)));
            log.info("Preloading " + repository.save(new Hotel(
                    "Hotel 2",
                    1)));
        };
    }
    @Bean
    CommandLineRunner initTimezone(TimezoneRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Timezone(
                    "UTC+1",
                    1)));
        };
    }
}