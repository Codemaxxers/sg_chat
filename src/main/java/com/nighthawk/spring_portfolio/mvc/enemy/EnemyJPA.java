package com.nighthawk.spring_portfolio.mvc.enemy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; 
 
@Repository
public interface EnemyJPA extends JpaRepository<Enemy, Long> {
    Enemy findByName(String name); // Update method name to match the property 'name'

    List<Enemy> findByType(String type);
}
 