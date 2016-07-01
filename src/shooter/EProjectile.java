package shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author 323443671
 */
public class EProjectile extends Projectile{
    
    private final int X_SPEED = 3;
    private final Color color = new Color (0,0,0,0);
    private double m , b;
    private int protagX, protagY, enemyX, enemyY;
    private double angle, dx, dy;
    
    public EProjectile(int x, int y, int width, int height, String path) {
        super(x,y, width, height, path);
    }
    
    public EProjectile(int x, int y, int width, int height, int protagX, int protagY, String path){
        super(x,y, width, height, path);
        this.angle = Math.atan2(y - protagY, x - protagX);
    }
    
    /*public EProjectile(Enemy enemy) {
        super(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
    }*/
 
    
   public void update(ControlPanel panel){

       if (this.getSpawn()){
            
           dx = (double) (Math.cos(angle) * 2);
           dy = (double) (Math.sin(angle) * 2); 

            //float angle = (float)Math.atan2(diffY, diffX);

            this.setX((int)(this.getX() - dx));
            this.setY((int)(this.getY() - dy));
           
       } else {
           this.setX(-100);
           this.setY(-100);
       }
    }
        public void paintComponent(Graphics2D g2) {
            hitbox.setLocation(this.getX(), this.getY());
            BufferedImage img = null;
            try {
                    img = this.enlarge(ImageIO.read(new File("62.png")), 1);
                } catch (IOException e) {
            }

            g2.drawImage(img, this.getX(), this.getY(), null);
            g2.setColor(color);
            g2.draw(hitbox);
            g2.fill(hitbox);
         }
}
