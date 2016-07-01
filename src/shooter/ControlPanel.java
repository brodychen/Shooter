package shooter;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ControlPanel extends JLayeredPane implements Runnable {

    private Protag protag;
    private ArrayList<Projectile> projects = new ArrayList<Projectile>();
    private Background back;
    private ArrayList<Enemy1> enemy = new ArrayList<Enemy1>();
    private boolean run = false;
    private ArrayList<Powerup> powerup = new ArrayList<Powerup>();
    private boolean[] actions = new boolean[7];
    private int timeProtag = 0;
    
    private boolean[] buff;

    private int powerUpChance = 2;
    private Random rand = new Random();
    protected int score = 0;

    private Clip bgm;
    private Clip kill;
    private Clip gg;
    private Clip newLevel;
    
    public enum STATE {

        GAME,
        MENU;
    }
    private STATE state = STATE.MENU;

    public ControlPanel() {
        protag = (new Protag(1, 150, 50, 15, 0, 0, 1, "Protag"));
        back = new Background();
        buff = new boolean[5];

        addKeyListener(new Control());
        
        try{
            //background music
            File file = new File("bgm.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);              
            bgm = AudioSystem.getClip();
            bgm.open(audioIn);
            FloatControl gainControl = 
            (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            
            file = new File("nextlevel.wav");
            audioIn = AudioSystem.getAudioInputStream(file);              
            newLevel = AudioSystem.getClip();
            newLevel.open(audioIn);
            
            file = new File("nextlevel.wav");
            audioIn = AudioSystem.getAudioInputStream(file);              
            newLevel = AudioSystem.getClip();
            newLevel.open(audioIn);
            
            file = new File("gameover.wav");
            audioIn = AudioSystem.getAudioInputStream(file);              
            gg = AudioSystem.getClip();
            gg.open(audioIn);
            
            file = new File("kill.wav");
            audioIn = AudioSystem.getAudioInputStream(file);              
            kill = AudioSystem.getClip();
            kill.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        run = true;
        thread.start();
    }

    public void stop() {
        run = false;
    }

    public void run() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

        main:
        while (run) {
            bgm.start();
            determineAction();
            protag.update(this);
            if (enemy.isEmpty()){
                newLevel.start();
                if (score >= 0 && score < 15){
                    int num = rand.nextInt(15) + 1;
                    for(int i = 0; i < num; i++){
                        enemy.add(new Enemy2(1040 + i * 60, rand.nextInt(400), 30, 32, 1, 0, 400, "94.png"));
                    }
                } else if (score >= 18 && score < 30){
                    int num = rand.nextInt(15) + 1;
                    for(int i = 0; i < num; i++){
                        enemy.add(new Enemy1(1040 + i * 60, rand.nextInt(400), 30, 32, 1, 0, 400, "1.png"));
                    }
                } else if (score >= 30){
                    
                    enemy.add(new Boss(1000, 150, 96, 64, 50, 0, 250, "100.png"));
                }
                
            }
            for (int i = 0; i < enemy.size(); i++) {
                if (enemy.get(i).getX() < this.getWidth() && enemy.get(i).getX() > 0 && enemy.get(i).getY() < this.getHeight()) {
                    if (enemy.get(i) instanceof Boss && enemy.get(i).getHealth() < 25) {
                        if (rand.nextInt(30) == 1 || actions[6]) {
                            projects.add(enemy.get(i).shoot(protag));
                        }
                    } else {
                        if (rand.nextInt(100) == 1 || actions[6]) {
                            projects.add(enemy.get(i).shoot(protag));
                        }
                    }
                }

                for (int j = 0; j < projects.size(); j++) {
                    if (enemy.get(i).collision(projects.get(j)) && rand.nextInt(powerUpChance) == 1) {
                        powerup.add(new Powerup(enemy.get(i).getX(), enemy.get(i).getY(), 25, 25, "20.png"));
                    }
                    if (protag.collision(projects.get(j))) {              
                        gg.start();      
                        bgm.stop();
                        JOptionPane.showMessageDialog(topFrame, "Game Over");
                        run = false;
                        restart();
                        break main;
                    }
                    projects.get(j).update(this);
                    if (projects.get(j).getX() <= 0 || projects.get(j).getY() <= 0 || projects.get(j).getY() >= this.getHeight()
                            || projects.get(j).getX() >= this.getWidth() - 10 || enemy.get(i).collision(projects.get(j))) {
                        if (enemy.get(i).collision(projects.get(j))){
                            kill.start();
                        }
                        projects.get(j).deSpawn();
                    }
                }
                
                for (int k = 0; k < powerup.size(); k++) {
                    if (protag.collision(powerup.get(k))) {
                        buff = Arrays.copyOf(powerup.get(k).collision(protag, buff), 5);
                    }
                }

                if (protag.collision(enemy.get(i))) {
                    bgm.stop();
                    JOptionPane.showMessageDialog(topFrame, "Game Over");
                    restart();
                    
                    run = false;
                    break main;
                }

                Shooter.label.setText("Enemies Killed: " + String.valueOf(score));
                Shooter.powerup1.setEnabled(true);
                
                enemy.get(i).update(this);               
            }
            repaint();
            
            try {
                Thread.sleep(17);
            } catch (Exception e) {
            }
            
            remove();
        }
    }
    
    public void remove(){
        Iterator<Enemy1> iterE = enemy.iterator();
            
        while(iterE.hasNext()){
            Enemy1 e = iterE.next();
            if(e.getHealth() <= 0 || e.getX() < 0){
                    iterE.remove();
                }
        }
            
        Iterator<Projectile> iterP = projects.iterator();
        
        while(iterP.hasNext()){
            Projectile p = iterP.next();
            if(p.getSpawn() == false){
                    iterP.remove();
                }            
        }
        
        Iterator<Powerup> iterPo = powerup.iterator();
        while (iterPo.hasNext()) {
            Powerup p = iterPo.next();
            if (p.getSpawn() == false) {
                iterPo.remove();
            }
        }
    }
    
    public void restart(){
        enemy.clear();
        projects.clear();
        powerup.clear();
        buff = new boolean[5];
        
        Shooter.powerup1.setEnabled(false);
        Shooter.powerup2.setEnabled(false);
        Shooter.powerup3.setEnabled(false);
        Shooter.powerup4.setEnabled(false);
        
        for(int i = 0; i < actions.length; i++){
            actions[i] = false;
        }
        
        score = 0;
        Shooter.label.setText("Enemies Killed: " + String.valueOf(score));
        
        protag = (new Protag(1, 150, 50, 15, 0, 0, 1, "Protag"));
        back = new Background();
        
        try{
            //background music
                File file = new File("/src/resources/bgm.wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);              
                bgm = AudioSystem.getClip();
                bgm.open(audioIn);
                FloatControl gainControl = 
                (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20.0f);
             } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
             } catch (IOException e) {
                e.printStackTrace();
             } catch (LineUnavailableException e) {
                e.printStackTrace();
             }
        
        addKeyListener(new Control());
        
        start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        back.draw(g);
        protag.paintComponent(g2);

        for (int i = 0; i < enemy.size(); i++) {
            enemy.get(i).paintComponent(g2);
        }
        for (int i = 0; i < projects.size(); i++) {
            projects.get(i).paintComponent(g2);
        }

        for (int i = 0; i < powerup.size(); i++) {
            powerup.get(i).paintComponent(g2);
        }

    }

//    public static void main(String[] args) {
//        //ControlPanel control = new ControlPanel();
//    }
    public void determineAction() {
        if (actions[0]) {
            protag.moveLeft();
        } else if (actions[2]) {
            protag.moveRight();
        } else {
            protag.stopRight();
            protag.stopLeft();
        }
        if (actions[3]) {
            protag.moveDown();
        } else if (actions[4]) {
            protag.moveUp();
        } else {
            protag.stopDown();
            protag.stopUp();
        }

        if (actions[5]) {
            if (timeProtag <= 0) {
                timeProtag = 100;

                projects.add(new Projectile(protag.getX(), protag.getY() + 15, 10, 10, "61.png"));
                for (int i = 0; i < buff.length; i++) {
                    if (buff[4]) {
                        protag.speedUp(buff[0]);
                        buff[4] = false;
                    }
                    if (buff[0]) {
                        Shooter.powerup2.setEnabled(true);
                        projects.add(new Projectile(protag.getX(), protag.getY() + 5, 10, 10, "61.png"));
                    }
                    if (buff[1]) {
                        Shooter.powerup3.setEnabled(true);
                        projects.add(new Projectile(protag.getX(), protag.getY() - 10, 10, 10, "61.png"));
                    }
                    if (buff[2]) {
                        Shooter.powerup4.setEnabled(true);
                        projects.add(new Projectile2(protag.getX(), protag.getY(), 10, 10, "61.png"));
                    }
                }
                try {
                    // Open an audio input stream.           
                    File soundFile = new File("proShoot.wav"); //you could also get the sound file with an URL
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                    // Get a sound clip resource.
                    Clip clip = AudioSystem.getClip();
                    // Open audio clip and load samples from the audio input stream.
                    clip.open(audioIn);
                    clip.start();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            } else {
                timeProtag -= 10;
            }
        }

    }

    public Background getBack() {
        return back;
    }

    private class Control implements KeyListener {

        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    actions[0] = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    actions[2] = true;
                    break;
                case KeyEvent.VK_DOWN:
                    actions[3] = true;
                    break;
                case KeyEvent.VK_UP:
                    actions[4] = true;
                    break;
                case KeyEvent.VK_X:
                    actions[5] = true;
                    break;
                case KeyEvent.VK_Z:
                    actions[6] = true;
                    break;
            }
        }

        public void keyReleased(KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    actions[0] = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    actions[2] = false;
                    break;
                case KeyEvent.VK_DOWN:
                    actions[3] = false;
                    break;
                case KeyEvent.VK_UP:
                    actions[4] = false;
                    break;
                case KeyEvent.VK_X:
                    actions[5] = false;
                    break;
                case KeyEvent.VK_Z:
                    actions[6] = false;
                    break;
            }
        }

        public void keyTyped(KeyEvent event) {
        }
    }
//	private class NewBall implements MouseListener {
//		public void mouseClicked(MouseEvent event) {
//			protags.add(new Protag(event.getX(), event.getY(), 40, Color.WHITE, 0, 0, ""));
//		}
//		
//		public void mousePressed(MouseEvent event) {
//		}
//
//		public void mouseReleased(MouseEvent event) {
//		}
//
//		public void mouseEntered(MouseEvent event) {
//		}
//
//		public void mouseExited(MouseEvent event) {
//		}
//	}
}
