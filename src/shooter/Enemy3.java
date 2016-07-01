package shooter;

public class Enemy3 extends Enemy1{
    
    public Enemy3(int x, int y, int width, int height, double health, int ceil, int floor, String path){
        super(x,y, width, height,health, ceil, floor, path);
    }
    
    public Enemy3(int x){
        super(x,0, 50, 50, 15, 0, 250, "0.png");
    }

    
    public void update(ControlPanel panel) {
        if(this.getX() <= 0){
            this.health = 0;
        }
        if (this.getHealth() <= 0 && this.getX() != -50) {
            panel.score += 1;
            this.setX(-50);
        } else if(this.getHealth() <= 0 && this.getX() == -50){
            this.setX(-50);
            this.setY(-50);
        } else {
            int x = this.getX();
            int y = this.getY();

            if(x > 800 && y == 100){
                this.setX(this.getX() - 1);
            } else if (x <= 800 && x > 700 && y >= 100 && y < 200){
                this.setX(this.getX() - 1);
                this.setY(this.getY() + 1);
            } else if (y == 200 && x >= 700 && x < 900){
                this.setX(this.getX() + 1);
            } else if (x == 900 && y == 200){
                this.setY(this.getY() + 2);
            }else {
                this.setX(this.getX() - 2);
            }
        }
    }
}
