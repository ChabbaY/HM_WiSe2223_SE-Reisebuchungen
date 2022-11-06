package com.chabbay.main;

import com.chabbay.data.Hotel;
import com.chabbay.data.HotelRepository;
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
    CommandLineRunner initDatabase(HotelRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Hotel("Hotel 1")));
            log.info("Preloading " + repository.save(new Hotel("Hotel 2")));
        };
    }
}