package com.nighthawk.spring_portfolio.mvc.enemy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

        enemies.add(new Enemy("Pixie", 10, 1, 1, 1, "Grass"));
        enemies.add(new Enemy("Troll", 20, 5, 2, 2, "Grass"));
        enemies.add(new Enemy("Golem", 30, 2, 5, 5, "Grass"));
        enemies.add(new Enemy("Goblin", 15, 10, 2, 5, "Dark"));
        enemies.add(new Enemy("Ghost", 25, 8, 5, 7, "Dark"));
        enemies.add(new Enemy("Dragon", 35, 12, 8, 10, "Dark"));
        enemies.add(new Enemy("Snow Man", 35, 15, 10, 10, "Snow"));
        enemies.add(new Enemy("Snow Golem", 35, 20, 10, 12, "Snow"));
        enemies.add(new Enemy("Ice Wizard", 45, 25, 10, 15, "Snow"));
        return enemies;
    }


    public static List<Enemy> init() {
        return createInitialData();
    }
}
