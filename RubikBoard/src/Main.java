package src;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;



public class Main 
{
    
    public static void main(String[] args)
    {
        
        Board b;
        b = new Board();
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.getContentPane().setBackground(Color.LIGHT_GRAY);
        b.setSize(300, 150);
        b.setVisible(true);
        b.setTitle("Rubik Board");
        b.setLocationRelativeTo(null);
        JLabel label = new JLabel (" Select Size of Board");
        b.add(label);
    }
    
}
