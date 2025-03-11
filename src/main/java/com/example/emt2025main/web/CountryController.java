package com.example.emt2025main.web;

import com.example.emt2025main.model.Country;
import com.example.emt2025main.service.CountyService;
import com.example.emt2025main.web.dto.CountryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountyService countryService;

    public CountryController(CountyService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@PathVariable Long id){
        Country country = this.countryService.findById(id);
        if (country != null) {
            return ResponseEntity.ok(country);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addNewCountry(@RequestBody CountryDto countryDto){
        Country country = this.countryService.create(countryDto.name, countryDto.continent);
        if (country != null) {
            return ResponseEntity.ok(country);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id){
        this.countryService.delete(id);

        return ResponseEntity.ok().build();
    }
}
