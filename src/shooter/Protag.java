package shooter;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Rectangle;

public class Protag extends Unit{
    private final Color color = new Color (0,0,0,0);

    
    public Protag(int x, int y, int width, int height, int xSpeed, int ySpeed, double health, String identify) {
        super(x, y, width, height, xSpeed, ySpeed, health, identify);
    }
    
    public void update(ControlPanel panel) {
        this.setX(this.getX() + this.getXSpeed());
        this.setY(this.getY() + this.getYSpeed());
        // Left wall impact
        if (this.getX() + this.getXSpeed()< 0) {
            this.setX(0);
        } // Right wall impact
        else if (this.getX() +this.getXSpeed() > panel.getWidth() - this.getWidth()  - 1) {
            this.setX(panel.getWidth() - this.getWidth() - 1);
        } 
        else if (this.getY() +this.getYSpeed()< 0) {
            this.setY(0);
        } // No horizontal impact
        
        // Floor impact
        if (this.getY()  > panel.getHeight() - this.getHeight()  - 1) {
            this.setY(panel.getHeight() - this.getHeight() - 1);
        }
    }
     /*private AlphaComposite makeComposite(float alpha) {
            int type = AlphaComposite.SRC_OVER;
     return(AlphaComposite.getInstance(type, alpha));
    }*/

//    public void shoot() {
//        super.paintComponent(g);
//        Projectile p = new Projectile(this.getX(), this.getY());
//        p.paintComponent();
//    }
    public boolean collision(Unit p) {
        if (p instanceof EProjectile || p instanceof Enemy1 || p instanceof Powerup) {
            if (hitbox.intersects(p.getRectangle())) {
                
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void speedUp(boolean powerup) {
        if (powerup) {
            this.setXSpeed(this.getXSpeed() + 5);
            this.setYSpeed(this.getYSpeed() + 5);
        }
    }
    
    public void paintComponent(Graphics2D g2) {
        hitbox.setLocation(this.getX() + 20, this.getY() + 15);
        BufferedImage img = null;
        try {
                img = this.enlarge(ImageIO.read(new File("sprites.png")), 1);
            } catch (IOException e) {
        }
        g2.setColor(color);   
        g2.draw(hitbox);
        g2.fill(hitbox);
        
        g2.drawImage(img, this.getX(), this.getY(), null);

    }
}