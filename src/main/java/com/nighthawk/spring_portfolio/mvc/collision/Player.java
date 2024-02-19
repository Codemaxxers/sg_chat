package com.nighthawk.spring_portfolio.mvc.collision;

import java.awt.Graphics2D;
import java.util.HashMap;

import java.awt.Image;
import java.util.Map;

import javax.swing.ImageIcon;


public class Player extends Entity{
    GamePanel gp; 
    KeyHandler keyH; 
    
    private int currentSpriteFrame = 0;
    private Map<String, Image[]> sprites;  // Map to store sprite animations
    private String currentDirection;  // To keep track of the current direction
    private int movementsCount = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp; 
        this.keyH = keyH; 
        setDefaultValues();
        loadSprites();
    }

    //default value method 

    public void setDefaultValues() {
        x = 100; 
        y = 100; 
        speed = 4; 

    }



    public void update() {
        if (keyH.upPressed) {
            y -= speed;
            currentDirection = "up";
            updateSpriteFrame();
        } else if (keyH.downPressed) {
            y += speed;
            currentDirection = "down";
            updateSpriteFrame();
        } else if (keyH.leftPressed) {
            x -= speed;
            currentDirection = "left";
            updateSpriteFrame();
        } else if (keyH.rightPressed) {
            x += speed;
            currentDirection = "right";
            updateSpriteFrame();
        }
        movementsCount++;
        if (movementsCount >= 20) {
            movementsCount = 0;
            currentSpriteFrame = (currentSpriteFrame + 1) % 2;  // Alternating between 0 and 1
        }

        
    }

    private void updateSpriteFrame() {
        // Update the sprite frame based on the direction
        Image[] directionSprites = sprites.get(currentDirection);
        currentSpriteFrame = (currentSpriteFrame + 1) % directionSprites.length;
    }


    private void loadSprites() {
        sprites = new HashMap<>();
    
        sprites.put("up", new Image[]{
                new ImageIcon(getClass().getResource("/com/nighthawk/spring_portfolio/mvc/collision/sprites/boy_up_1.png")).getImage(),
                new ImageIcon(getClass().getResource("/com/nighthawk/spring_portfolio/mvc/collision/sprites/boy_up_2.png")).getImage()
        });
    
        sprites.put("down", new Image[]{
                new ImageIcon(getClass().getResource("/com/nighthawk/spring_portfolio/mvc/collision/sprites/boy_down_1.png")).getImage(),
                new ImageIcon(getClass().getResource("/com/nighthawk/spring_portfolio/mvc/collision/sprites/boy_down_2.png")).getImage()
        });
    
        sprites.put("left", new Image[]{
                new ImageIcon(getClass().getResource("/com/nighthawk/spring_portfolio/mvc/collision/sprites/boy_left_1.png")).getImage(),
                new ImageIcon(getClass().getResource("/com/nighthawk/spring_portfolio/mvc/collision/sprites/boy_left_2.png")).getImage()
        });
    
        sprites.put("right", new Image[]{
                new ImageIcon(getClass().getResource("/com/nighthawk/spring_portfolio/mvc/collision/sprites/boy_right_1.png")).getImage(),
                new ImageIcon(getClass().getResource("/com/nighthawk/spring_portfolio/mvc/collision/sprites/boy_right_2.png")).getImage()
        });
    }
    


    public void draw(Graphics2D g2) {

        Image[] directionSprites = sprites.get(currentDirection);
        Image currentSprite = directionSprites[currentSpriteFrame];
        // Draw the appropriate sprite at the player's position
        g2.drawImage(currentSprite, x, y, gp.tileSize, gp.tileSize, null);

    }

}
