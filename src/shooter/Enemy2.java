package shooter;

public class Enemy2 extends Enemy1{
    
    public Enemy2(int x, int y, int width, int height, double health, int ceil, int floor, String path){
        super(x,y, width, height,health, ceil, floor, path);
    }
    
    public void update(ControlPanel panel) {
        if (this.getHealth() <= 0 && this.getX() != -50) {
            panel.score += 1;            
        }else {
        this.setX(this.getX() - 1);
        this.setY((int)(Math.pow((1 / 50) * this.getX(), 2) + this.getY()));
        }
    }
    
    public EProjectile shoot(Protag protag){
        return new EProjectile(this.getX(), this.getY(), 10, 10, protag.getX(), protag.getY(), "61.png");
    }
}
