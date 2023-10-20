package SnakeGame;

import java.awt.*;
import java.util.Random;

public class Apple {
    private int appleX;
    private int appleY;
    private int unitSize;
    private int panelWidth;
    private int panelHeight;
    private Random random;

    public Apple(int unitSize, int panelWidth, int panelHeight) {
        this.unitSize = unitSize;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.random = new Random();
        randomApple();
    }

    public void randomApple() {
        int maxX = (panelWidth - unitSize) / unitSize; //The "maximal" x position
        int maxY = (panelHeight - unitSize) / unitSize; //The "maximal" y position
        
        appleX = random.nextInt(maxX) * unitSize;
        appleY = random.nextInt(maxY) * unitSize;
    }
    


    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public void draw(Graphics graphics) {
        graphics.setColor(new Color(255, 150, 150));
        graphics.fillRect(appleX, appleY, unitSize, unitSize);
    }
}
