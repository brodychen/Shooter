package shooter;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Unit{
    
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    protected String identify;
    private double health;
    private int width;
    private int height;
    protected Rectangle hitbox;
    protected String path;
    
    public Unit(int x, int y, int width, int height, int xSpeed, int ySpeed, double health, String identify) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.identify = identify;
        this.health = health;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);
    }
    
    public Unit(int x, int y, int width, int height, String path) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.path = path;
        this.hitbox = new Rectangle(x, y, width, height);
    }
    
    public String getPath(){
        return path;
    }
    
    public Rectangle getRectangle(){
        return hitbox;
    }
    
    public void stopRight() {
        xSpeed = 0;
    }

    public void stopLeft() {
        xSpeed = 0;
    }

    public void stopUp() {  
        ySpeed = 0;        
    }

    public void stopDown() {
        ySpeed = 0;
    }
    

    public void moveRight() {
        xSpeed = 5;
    }

    public void moveLeft() {
        xSpeed = -5;        
    }

    public void moveUp() {  
        ySpeed = -5;        
    }

    public void moveDown() {
        ySpeed = 5;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public double getHealth() {
        return health;
    }
    
    public int getXSpeed() {
        return xSpeed;
    }
    
    public int getYSpeed() {
        return ySpeed;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }
    
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }
            
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    public void update(ControlPanel panel, boolean hit) {
        
    }
    public void paintComponent(Graphics2D g2) {
        
    }
    
    public static BufferedImage enlarge(BufferedImage image, int n) {
        
       int w = n * image.getWidth();
       int h = n * image.getHeight();
        
       BufferedImage enlargedImage =
               new BufferedImage(w, h, image.getType());
        
       for (int y=0; y < h; ++y)
           for (int x=0; x < w; ++x)
               enlargedImage.setRGB(x, y, image.getRGB(x/n, y/n));
        
       return enlargedImage;
   }
}
