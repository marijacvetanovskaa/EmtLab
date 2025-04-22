package com.example.emt2025main.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.authors_per_country")
@Immutable
public class AuthorsByCountryView {

    @Id
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "author_count")
    private Long authorCount;
}
