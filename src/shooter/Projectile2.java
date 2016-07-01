/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

public class Projectile2 extends Projectile{
        
    private int xSpeed = 5;
    private int ySpeed = 5;
    private Color color = Color.RED;
    private Rectangle hitbox;
    private int counter = 5;
    
    public Projectile2(int x, int y, int width, int height, String path) {
        super(x,y, width, height, path);
    }
    
    @Override
    public void update(ControlPanel panel){
        if (xSpeed == 1) {
            xSpeed = 0;
        } else {
            xSpeed = 1;
        }
        this.ySpeed = (int) Math.pow(ySpeed, 0.5);
        if (counter >= 5) {
            counter = 0;
            this.xSpeed = (int) Math.pow(xSpeed, 0.2);
        }
        counter++;
        this.setX(this.getX() + xSpeed);
        this.setY(this.getY() + ySpeed);
    }
    
    public Rectangle getRectangle() {
        return hitbox;
    }
    
    public void paintComponent(Graphics2D g2) {
        hitbox = new Rectangle(this.getX() + 5, this.getY() + 20, this.getWidth(), this.getHeight());
        //g2.setComposite(new Composite);
        g2.setColor(color);
        g2.fill(hitbox);
        g2.draw(hitbox);
    }
}
