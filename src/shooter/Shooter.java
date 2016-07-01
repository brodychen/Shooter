/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shooter;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author 325013621
 */
public class Shooter {
    private static JFrame frame;
    private static ControlPanel game;
    protected static JButton start;
    protected static JButton quit;
    protected static JButton help;
    protected static JLabel label;
    protected static JPanel top;
    private static JPanel bot;
    protected static JButton powerup1;
    protected static JButton powerup2;
    protected static JButton powerup3;
    protected static JButton powerup4;
    
    public static void main(String[] args){
        game = new ControlPanel();
        frame = new JFrame();
        label = new JLabel("Enemies Killed: " + String.valueOf(game.score));
        start = new JButton("Start");
        quit = new JButton("Quit");
        help = new JButton("How to Play");
        
        powerup1 = new JButton("1 Shot");
        powerup2 = new JButton("2 Shot");
        powerup3 = new JButton("3 Shot");
        powerup4 = new JButton("Diagonal Shot");
               
        bot = new JPanel();
        top = new JPanel();
        
        powerup1.setEnabled(false);
        powerup2.setEnabled(false);
        powerup3.setEnabled(false);
        powerup4.setEnabled(false);
        
        start.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {  

                start.setEnabled(false);
                game.requestFocus();
                game.start();
            }
        }); 
        
        quit.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {  
                System.exit(1);
            }
        });
        
        help.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {  
                JOptionPane.showMessageDialog(frame, "Kill 30 Enemies to Summonn Boss!");
            }
        });
        
        game.setSize(new Dimension(game.getBack().getImageWidth() / 2,game.getBack().getImageHeight()));
        game.setFocusable(true);
        game.setVisible(true);

        frame.setPreferredSize(new Dimension(game.getBack().getImageWidth() / 2,50 + game.getBack().getImageHeight()));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
        top.add(start);
        top.add(quit);
        top.add(help);
        

        bot.add(powerup1);
        bot.add(powerup2);
        bot.add(powerup3);
        bot.add(powerup4);
        bot.add(label);
        
        frame.add(top, BorderLayout.PAGE_START);
        frame.add(game, BorderLayout.CENTER);
        frame.add(bot, BorderLayout.PAGE_END);
        frame.pack();
        
       
    }
    
    public void actionPerformed(ActionEvent e) {
        }
}
