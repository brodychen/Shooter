/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Powerup extends Projectile{
         
    private Color color = new Color(0,0,0,0);
    
    public Powerup(int x, int y, int width, int height, String path) {
        super(x, y, width, height, path);
    }
    
    public boolean[] collision(Protag p, boolean[] check){
        if (hitbox.intersects(p.getRectangle())) {
            for (int i = 0; i < check.length; i++) {
                if (!(this.getX() < 0)) {
                    if (check[i] == false){
                        check[i] = true;
                        break;
                    }
            }
            }
            this.setX(-100);
            this.setY(-100);
        }
        return check;
    }
    
    public void paintComponent(Graphics2D g2) {
        hitbox.setLocation(this.getX(), this.getY());
        BufferedImage img = null;
        try {
            img = this.enlarge(ImageIO.read(new File(this.getPath())), 1);
            } catch (IOException e) {
        }
        g2.drawImage(img, this.getX(), this.getY(), null);
        g2.setColor(color);
        g2.fill(hitbox);
        g2.draw(hitbox);
    }
}
