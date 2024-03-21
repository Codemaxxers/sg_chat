package com.nighthawk.spring_portfolio.mvc.terms;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TermJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Term> findByCourse(String courseName) {
        String sql = "SELECT * FROM " + courseName; // Ensure courseName is validated or mapped
        Query query = entityManager.createNativeQuery(sql, Term.class);
        return query.getResultList();
    }

    public Term findRandomTerm(String courseName) {
        String sql = "SELECT * FROM " + courseName + " ORDER BY RANDOM() LIMIT 1"; 
        Query query = entityManager.createNativeQuery(sql, Term.class);
        return (Term) query.getSingleResult();
    }
}
