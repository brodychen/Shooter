package shooter;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Background extends JComponent{
    private BufferedImage i;    
    private int x;
    private int y;
    private int xSpeed = 5;
    
    public Background(){
        this(0,0);
    }
    
    public Background(int x, int y){
        this.x = x;
        this.y = y;
        
        try{
            i = ImageIO.read(new File("background.png"));
        } catch (Exception e){            
        }
    }
    
    public void draw(Graphics g) {
        g.drawImage(i, x, y, i.getWidth(), i.getHeight(), null);
        this.x -= xSpeed;
        if(this.x <= -1 * i.getWidth() / 2){
            this.x = 0;
        }
    }
    
    public void setX(int x) {
        this.x = x;
    }
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
    public int getX() {
        return this.x;
    }
    public int getXSpeed() {
        return this.xSpeed;
    }
    public int getY() {
        return this.y;
    }
    public int getImageWidth() {
        return i.getWidth();
    }
    public int getImageHeight() {
        return i.getHeight();
    }
    
 
}
