package com.nighthawk.spring_portfolio.mvc.terms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/api/terms")
public class TermController {

    @Autowired
    private TermService termService;

    @Autowired
    private TermRepository termRepository;

    @GetMapping
    public List<Term> getAllTerms() {
        return termService.getAllTerms();
    }

    //Get a random term
    @GetMapping("/randomTerm/{unit}")
    public Term getRandomTermByUnit(@PathVariable String unit) {
        // Fetch all terms for the given unit
        List<Term> terms = termRepository.findAllByUnit(unit);
        
        // Check if there are any terms for the given unit
        if (terms.isEmpty()) {
            // Handle the case when there are no terms for the given unit
            // For example, you can return an error message or throw an exception
            throw new RuntimeException("No terms found for unit: " + unit);
        }
        
        // Generate a random index within the range of the list size
        int randomIndex = new Random().nextInt(terms.size());
        
        // Return the randomly selected term
        return terms.get(randomIndex);
    }

    //Add term
    @PostMapping("/makeTerm")
    @PreAuthorize("isAuthenticated()")
    public Term makeTerm(@RequestBody Term term) {
        return termRepository.save(term);
    }
    
    //Delete term
    @DeleteMapping("/deleteTerm/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteTerm(@PathVariable Long id) {
        termRepository.deleteById(id);
    }
}
