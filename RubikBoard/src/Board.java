package src;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JFrame {
    private JButton Large; // button for Large size
    private JButton Medium; // button for Medium size

    private JButton little; // button for small size

    // Constructor
    public Board() {

        setLayout(new FlowLayout());

        little = new JButton("Little: 2x2 size");
        little.setBackground(Color.PINK);
        add(little);
        smallBoard s1 = new smallBoard();
        little.addActionListener(s1);

        Medium = new JButton("Medium: 4x4 size");
        Medium.setBackground(Color.GREEN);
        add(Medium);
        MediumBoard s2 = new MediumBoard();
        Medium.addActionListener(s2);

        Large = new JButton("Large: 6x6 size");
        Large.setBackground(Color.RED);
        add(Large);
        LargeBoard s3 = new LargeBoard();
        Large.addActionListener(s3);

    }

    public class LargeBoard implements ActionListener {
        public void actionPerformed(ActionEvent s3) {
            RubikDesign Design;
            Design = new RubikDesign(6);
            Design.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Design.setLocationRelativeTo(null);
            Design.setSize(600, 600);
            Design.setVisible(true);
            Design.setTitle("6x6 Size");
        }
    }

    public class MediumBoard implements ActionListener {
        public void actionPerformed(ActionEvent s2) {
            RubikDesign Design;
            Design = new RubikDesign(4);
            Design.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Design.setLocationRelativeTo(null);
            Design.setSize(700, 700);
            Design.setVisible(true);
            Design.setTitle("4x4 Size");
        }
    }

    public class smallBoard implements ActionListener {
        public void actionPerformed(ActionEvent s1) {
            RubikDesign Design;
            Design = new RubikDesign(2);
            Design.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Design.setLocationRelativeTo(null);
            Design.setSize(250, 250);
            Design.setVisible(true);
            Design.setTitle("2x2 Size");
        }
    }

}
