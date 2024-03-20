package com.nighthawk.spring_portfolio.mvc.terms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {

    @Query("SELECT q FROM Term q WHERE q.unit = :unit")
    List<Term> findAllByUnit(@Param("unit") String unit);
}