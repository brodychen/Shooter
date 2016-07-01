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
public class EProjectile2 extends EProjectile{
    
    private final int X_SPEED = 1;
    
    public EProjectile2(int x, int y, int width, int height, String path) {
        super(x,y, width, height, path);
    }
    
    public EProjectile2(int x, int y, int width, int height, int protagX, int protagY, String path){
        super(x,y, width, height, path);
    }
    
    /*public EProjectile(Enemy enemy) {
        super(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
    }*/
 
    
   public void update(ControlPanel panel){
       if (this.getSpawn()){
            this.setX(this.getX() - X_SPEED);
       }
    }
}
