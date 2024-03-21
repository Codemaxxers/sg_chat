package com.nighthawk.spring_portfolio.mvc.terms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;

    public List<Term> getAllTerms() {
        return termRepository.findAll();
    }

    @Autowired
    private TermJpaRepository customTermRepository;

    public List<Term> getTermsByCourse(String courseName) {
        return customTermRepository.findByCourse(courseName);
    }
    public Term getRandomTerm(String courseName) {
        return customTermRepository.findRandomTerm(courseName);
    }


    // Other business logic methods
}
