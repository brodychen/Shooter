package shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemy1 extends Unit{
    private final Color color = new Color (0,0,0,0);
    private boolean up = false;
    private final int ceil;
    private final int floor;
    protected double health;
    
    public Enemy1(int x, int y, int width, int height, double health, int ceil, int floor, String path){
        super(x,y, width, height, path);
        this.ceil = ceil;
        this.floor = floor;
        this.health = health;
    }
    
    /*public int getIntX(){
        return X_INT;
    }
    
    public int getIntY(){
        return Y_INT;
    }*/
    
    public boolean getDirecton(){
        return up;
    }
    
    @Override
    public double getHealth() {
        return health;
    }
    
    public void update(ControlPanel panel) {
        if (this.getHealth() <= 0) {
            panel.score += 1;
        } else {
            this.setX(this.getX() - 1);
            if (this.getY() + 3 >  this.floor){
                up = true;
            } else if (this.getY() - 3 < this.ceil){
                up = false;
            }
            if (up  == true){
                this.setY(this.getY() - 5);
                }
            else{
                this.setY(this.getY() + 5);
            }
        }
    }
    
    public boolean collision(Projectile g) {
        if (!(g instanceof EProjectile)){
            if (hitbox.intersects(g.getX(), g.getY(), g.getWidth(), g.getHeight())) {
                health -= 1;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean attack(){
        return this.getX() < 1140;
    }
    
    
    public EProjectile shoot(Protag protag){
        return new EProjectile2(this.getX(), this.getY(), 10, 10, protag.getX(), protag.getY(), "62.png");
    }
    
    public void paintComponent(Graphics2D g2) {
        hitbox.setLocation(this.getX(), this.getY());
            BufferedImage img = null;
            try {
                    img = Enemy1.enlarge(ImageIO.read(new File(this.getPath())), 2);
                } catch (IOException e) {
            }

            
            g2.setColor(color);
            g2.draw(hitbox);
            g2.fill(hitbox);
            g2.drawImage(img, this.getX(), this.getY(), null);
    }
}
