package com.example.emt2025main.service;

import com.example.emt2025main.model.Book;
import com.example.emt2025main.model.Category;
import org.springframework.data.domain.Page;
import com.example.emt2025main.model.exceptions.InvalidBookIdException;
import com.example.emt2025main.model.exceptions.InvalidCountryIdException;
import java.util.List;

public interface BookService {

    /**
     * @return List of all book in the database
     */
    List<Book> listAll();

    /**
     * @param id The id of the book that we want to obtain
     * @return The book with the appropriate id
     * @throws InvalidBookIdException when there is no reservation with the given id
     */
    Book findById(Long id);

    /**
     * This method is used to create a new book, and save it in the database.
     *
     * @param name
     * @param category
     * @param countryId
     * @param availableCopies
     * @param author
     * @return The book that is created. The id should be generated when the book is created.
     * @throws InvalidCountryIdException when there is no hotel with the given id
     */
    Book create(String name, Category category, Long countryId, Integer availableCopies, Long author);

    /**
     * This method is used to update a reservation, and save it in the database.
     *
     * @param id          The id of the book that is being updated
     * @param name
     * @param category
     * @param countryId
     * @param availableCopies
     * @param author
     * @return The book that is updated.
     * @throws InvalidBookIdException when there is no book with the given id
     * @throws InvalidCountryIdException       when there is no county with the given id
     */
    Book update(Long id, String name, Category category, Long countryId, Integer availableCopies, Long author);

    /**
     * @param id
     * @return The book that is deleted.
     * @throws InvalidBookIdException when there is no book with the given id
     */
    Book delete(Long id);

    /**
     * This method should implement the logic for renting a book,
     * by deducting one available copy to the availableCopies.
     *
     * @param id
     * @return is request ok.
     * @throws InvalidBookIdException when there is no book with the given id
     */
    boolean rented(Long id);

    /**
     * Returns a page of books that match the given criteria.
     *
     * @param name
     * @param category
     * @param countryId
     * @param authorId
     * @param pageNum
     * @param pageSize
     * @return The page of books that match the given criteria.
     */
    Page<Book> findPage(String name, Category category, Long countryId, Long authorId, int pageNum, int pageSize);
}
