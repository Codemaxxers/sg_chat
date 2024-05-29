package com.nighthawk.spring_portfolio.mvc.enemy;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Enemy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;
    private int health;
    private int attack;
    private int defense;
    private int level;
    private String type;

    public Enemy(String name, int health, int attack, int defense, int level, String type) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.type = type;
    }
  
    public static List<Enemy> createInitialData() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        enemies.add(new Enemy("Pixie", 100, 10, 1, 1, "Grass"));
        enemies.add(new Enemy("Troll", 200, 50, 2, 2, "Grass"));
        enemies.add(new Enemy("Golem", 300, 20, 5, 5, "Grass"));
        enemies.add(new Enemy("Goblin", 150, 100, 2, 9, "Dark"));
        enemies.add(new Enemy("Ghost", 250, 80, 5, 12, "Dark"));
        enemies.add(new Enemy("Dragon", 350, 120, 8, 14, "Dark"));
        enemies.add(new Enemy("Snow Man", 350, 150, 10, 17, "Snow"));
        enemies.add(new Enemy("Snow Golem", 350, 200, 10, 20, "Snow"));
        enemies.add(new Enemy("Ice Wizard", 450, 250, 10, 22, "Snow"));
        return enemies;
    }


    public static List<Enemy> init() {
        return createInitialData();
    }
}
