package com.example.emt2025main.service.domain.impl;

import com.example.emt2025main.events.AuthorEvent;
import com.example.emt2025main.model.domain.Author;
import com.example.emt2025main.model.projections.AuthorProjection;
import com.example.emt2025main.model.views.AuthorsByCountryView;
import com.example.emt2025main.repository.AuthorRepository;
import com.example.emt2025main.repository.AuthorsByCountryRepository;
import com.example.emt2025main.service.domain.AuthorService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorsByCountryRepository authorsByCountryRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorsByCountryRepository authorsByCountryRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.authorRepository = authorRepository;
        this.authorsByCountryRepository = authorsByCountryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> create(Author author) {
        Optional<Author> createdAuthor = Optional.of(this.authorRepository.save(author));
        this.applicationEventPublisher.publishEvent(new AuthorEvent(author));
        return createdAuthor;
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        Optional<Author> updatedAuthor = authorRepository.findById(id).map(existingAuthor -> {
            if (author.getName() != null) {
                existingAuthor.setName(author.getName());
            }
            if (author.getSurname() != null) {
                existingAuthor.setSurname(author.getSurname());
            }
            if (author.getCountry() != null) {
                existingAuthor.setCountry(author.getCountry());
            }
            return authorRepository.save(existingAuthor);
        });

        this.applicationEventPublisher.publishEvent(new AuthorEvent(author));
        return updatedAuthor;
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
        this.refreshMaterializedView(); // we delete by id so no source for event (has to be the author being deleted)
    }

    @Override
    public void refreshMaterializedView() {
        this.authorsByCountryRepository.refreshMaterializedView();
    }

    @Override
    public List<AuthorsByCountryView> getAuthorsByCountry() {
        return authorsByCountryRepository.findAll();
    }

    @Override
    public List<AuthorProjection> getAuthorNames() {
        return authorRepository.findAllBy();
    }
}
