package com.example.emt2025main.service;

import com.example.emt2025main.model.Country;
import com.example.emt2025main.model.exceptions.InvalidCountryIdException;

import java.util.List;

public interface CountyService {

    /**
     * @param id The id of the country that we want to obtain
     * @return The country with the appropriate id
     * @throws InvalidCountryIdException when there is no country with the given id
     */
    Country findById(Long id);

    /**
     * @return List of all country in the database
     */
    List<Country> listAll();

    /**
     * This method is used to create a new country, and save it in the database.
     *
     * @param name
     * @param continent
     * @return The country that is created. The id should be generated when the country is created.
     */
    Country create(String name, String continent);

    void delete(Long id);
}
