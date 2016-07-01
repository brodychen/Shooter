package shooter;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author 323443671
 */
public class Projectile extends Unit{
    
    private final int X_SPEED = 3;
    private final Color color = new Color (0,0,0,1);
    private boolean spawn = true;
    
    public Projectile(int x, int y, int width, int height, String path) {
        super(x,y, width, height, path);
    }
    
    public void update(ControlPanel panel){
        if (spawn) {
        this.setX(this.getX() + X_SPEED);
        } else {
            
        }
    }
    
    public Rectangle getRectangle() {
        return hitbox;
    }
       
    public boolean getSpawn() {
        return spawn;
    }
    
    public void deSpawn() {
        spawn = false;
    }
    
    public void paintComponent(Graphics2D g2) {
        hitbox.setLocation(this.getX(), this.getY());
            BufferedImage img = null;
            try {
                    img = ImageIO.read(new File(this.getPath()));
                } catch (IOException e) {
            }

            g2.drawImage(img, this.getX(), this.getY(), null);
            g2.setColor(color);
            g2.draw(hitbox);
            g2.fill(hitbox);
    }

}
