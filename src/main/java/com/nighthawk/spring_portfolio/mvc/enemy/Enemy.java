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

        enemies.add(new Enemy("Pixie", 100, 1, 1, 1, "Grass"));
        enemies.add(new Enemy("Troll", 200, 5, 2, 2, "Grass"));
        enemies.add(new Enemy("Golem", 300, 2, 5, 5, "Grass"));
        enemies.add(new Enemy("Goblin", 150, 10, 2, 5, "Dark"));
        enemies.add(new Enemy("Ghost", 250, 8, 5, 7, "Dark"));
        enemies.add(new Enemy("Dragon", 350, 12, 8, 10, "Dark"));
        enemies.add(new Enemy("Snow Man", 350, 15, 10, 10, "Snow"));
        enemies.add(new Enemy("Snow Golem", 350, 20, 10, 12, "Snow"));
        enemies.add(new Enemy("Ice Wizard", 450, 25, 10, 15, "Snow"));
        return enemies;
    }


    public static List<Enemy> init() {
        return createInitialData();
    }
}
