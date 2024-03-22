package com.nighthawk.spring_portfolio.mvc.enemy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/enemies")
public class EnemyController {

    private final EnemyJPA enemyRepository;

    @Autowired
    public EnemyController(EnemyJPA enemyRepository) {
        this.enemyRepository = enemyRepository;
    }

    @GetMapping
    public ResponseEntity<List<Enemy>> getAllEnemies() {
        List<Enemy> enemies = enemyRepository.findAll();
        return new ResponseEntity<>(enemies, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Enemy>> getEnemiesByType(@PathVariable String type) {
        List<Enemy> enemies = enemyRepository.findByType(type);
        return new ResponseEntity<>(enemies, HttpStatus.OK);
    }
}
