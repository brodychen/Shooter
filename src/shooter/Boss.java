package shooter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Boss extends Enemy1{
    private final Color color = new Color (0,0,0,0);
    private boolean up = false;
    private Rectangle healthbar;
    
    public Boss(int x, int y, int width, int height, double health, int ceil, int floor, String path){
        super(x,y, width, height,health, ceil, floor, path);
        this.healthbar = new Rectangle(x, y, (int)this.getHealth(), 10);
    }

    
    public void update(ControlPanel panel) {
        if (this.getHealth() <= 0 && this.getX() != -50) {
            panel.score += 10000;
            this.path = "face.png";
        } else {
            int x = this.getX();
            int y = this.getY();
            if (x > 1040){
                this.setX(this.getX() - 1);
            } else if(x < 1040 && x > 800 && y == 150){
                this.setX(this.getX() - 5);
            } else {
                if (up){
                    if(this.getY() - 3 < 0){
                        up = false;
                    }
                    this.setY(this.getY() - 3);
                } else if (!up){
                    if (this.getY() + 3 > 421 - 65){
                        up = true;
                    }
                    this.setY(this.getY() + 3);
                }
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics2D g2) {
        hitbox.setLocation(this.getX(), this.getY());
        healthbar.setLocation(this.getX(), this.getY() + 70);
        healthbar.setSize((int)this.getHealth() * 2, 5);
        
            BufferedImage img = null;
            try {
                    img = ImageIO.read(new File(this.getPath()));
                } catch (IOException e) {
            }
            
            g2.setColor(color);
            g2.draw(hitbox);
            g2.fill(hitbox);
            
            g2.setColor(Color.RED);
            g2.draw(healthbar);
            g2.fill(healthbar);
            
            g2.drawImage(img, this.getX(), this.getY(), null);
    }
}
