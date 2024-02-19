package com.nighthawk.spring_portfolio.mvc.collision;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class Npc extends Entity implements MouseListener {
    private Image npcImage;
    private String speechText;
    private Boolean displaySpeech; 
    private GamePanel gamePanel;
    
    public Npc(int x, int y, String speechText, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.speechText = speechText;
        this.displaySpeech = false;
        this.gamePanel = gamePanel;  // Assign the GamePanel instance
        loadNPCImage();
        gamePanel.addMouseListener(this);  // Add MouseListener to the GamePanel
    }

    private void loadNPCImage() {
        npcImage = new ImageIcon(getClass().getResource("/com/nighthawk/spring_portfolio/mvc/collision/sprites/npc1.png")).getImage();
        npcImage = npcImage.getScaledInstance(npcImage.getWidth(null) / 2, npcImage.getHeight(null) / 2, Image.SCALE_DEFAULT);
    }

    public void draw(Graphics2D g2) {
        // Draw the NPC image at its position
        g2.drawImage(npcImage, x, y, null);

        if (displaySpeech) {
            // Draw speech bubble
            int bubbleWidth = 200;
            int bubbleHeight = 50;

            // Adjust the bubble position based on NPC position
            int bubbleX = x + npcImage.getWidth(null) + 5;
            int bubbleY = y - 10;

            // Draw the speech bubble
            g2.setColor(Color.WHITE);
            g2.fillRect(bubbleX, bubbleY, bubbleWidth, bubbleHeight);
            g2.setColor(Color.BLACK);
            g2.drawRect(bubbleX, bubbleY, bubbleWidth, bubbleHeight);

            // Draw the speech text
            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            g2.drawString(speechText, bubbleX + 10, bubbleY + 20);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Check if the click occurred on the NPC
        Rectangle npcBounds = new Rectangle(x, y, npcImage.getWidth(null), npcImage.getHeight(null));
        if (npcBounds.contains(e.getPoint())) {
            // Toggle the display of speech bubble
            displaySpeech = !displaySpeech;
            gamePanel.repaint();  // Trigger repaint to update the display
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
