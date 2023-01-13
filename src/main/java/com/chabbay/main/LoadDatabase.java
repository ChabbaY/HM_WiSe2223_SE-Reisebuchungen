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
    CommandLineRunner initCountry(CountryRepository repository) {//id 1
        return args -> {
            log.info("Preloading " + repository.save(new Country(
                    "Deutschland",
                    "Deutsch",
                    "DE")));
        };
    }
    @Bean
    CommandLineRunner initTimezone(TimezoneRepository repository) {//id 2
        return args -> {
            log.info("Preloading " + repository.save(new Timezone(
                    "UTC+1",
                    1)));
        };
    }
    @Bean
    CommandLineRunner initCountryTimezone(CountryTimezoneRepository repository) {//id 3
        return args -> {
            log.info("Preloading " + repository.save(new CountryTimezone(
                    1L,
                    2L)));
        };
    }
    @Bean
    CommandLineRunner initAddressInformation(AddressInformationRepository repository) {//id 4
        return args -> {
            log.info("Preloading " + repository.save(new AddressInformation(
                    "Herr",
                    "098 1234567",
                    "abc@def.gh")));
        };
    }
    @Bean
    CommandLineRunner initAddress(AddressRepository repository) {//id 5
        return args -> {
            log.info("Preloading " + repository.save(new Address(
                    "Hauptstrasse",
                    "1A",
                    "123-456",
                    "Musterstadt",
                    null,
                    4L,
                    1L,
                    2L)));
        };
    }
    @Bean
    CommandLineRunner initCustomer(CustomerRepository repository) {//id 6
        return args -> {
            log.info("Preloading " + repository.save(new Customer(
                    "C1234",
                    "Max",
                    "Mustermann",
                    "01.01.1999",
                    4L)));
        };
    }
}