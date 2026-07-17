package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        return countryRepository.findAll();
    }

    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        LOGGER.info("Start");
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException("Country not found with code: " + countryCode);
        }
        LOGGER.info("End");
        return result.get();
    }

    @Transactional
    public void addCountry(Country country) {
        LOGGER.info("Start");
        countryRepository.save(country);
        LOGGER.info("End");
    }

    @Transactional
    public void updateCountry(String code, String newName) throws CountryNotFoundException {
        LOGGER.info("Start");
        Country country = findCountryByCode(code);
        country.setName(newName);
        countryRepository.save(country);
        LOGGER.info("End");
    }

    @Transactional
    public void deleteCountry(String code) {
        LOGGER.info("Start");
        countryRepository.deleteById(code);
        LOGGER.info("End");
    }

    @Transactional
    public List<Country> findByNameContaining(String text) {
        LOGGER.info("Start");
        return countryRepository.findByNameContainingIgnoreCaseOrderByNameAsc(text);
    }
}