package shooter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

public class Enemy extends Unit{
    
    private Color color;
    private int weight, height;
    private int x;
    private int y;
    private int xSpeed;
    private Rectangle hitbox;
    private int ySpeed;
    
    public Enemy(int x, int y, int width, int height, int xSpeed, int ySpeed, Color color, int health, String identify) {
        super(x, y, width, height, xSpeed, ySpeed, health, identify);
        this.color = color;
    }
    
   public void update(ControlPanel panel){
     if (this.getX() + this.getXSpeed()< 0) {
         x -= xSpeed;
         y -= ySpeed;
     } else {
         x += xSpeed;
         y -=ySpeed;
     }
     this.setX(this.getX() + xSpeed);
     this.setY(this.getY() + ySpeed);
    }
    public void paintComponent(Graphics2D g2) {
        hitbox = new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g2.setColor(color);
        g2.fill(hitbox);
        g2.draw(hitbox);
    }
}
