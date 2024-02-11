package src;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class RubikDesign extends JFrame {
    int sizeOfBoard;
    long cnt = 0;

    //p board
    JLabel p[][];

    //buttons array which hold all buttons
    JButton btnWets[];
    JButton btnEast[];

    JButton btnNorth[];
    JButton btnSouth[];
   
    JPanel panel;

    JPanel leftButton;
    JPanel rightButton;
    JPanel NorthButton;
    JPanel downButton;


    public RubikDesign(int d) {
        sizeOfBoard = d;
        p = new JLabel[sizeOfBoard][sizeOfBoard];
        btnNorth = new JButton[sizeOfBoard];
        btnSouth = new JButton[sizeOfBoard];
        btnWets = new JButton[sizeOfBoard];
        btnEast = new JButton[sizeOfBoard];

        NorthButton = new JPanel();
        NorthButton.setLayout(new GridLayout(1, sizeOfBoard, 2, 2));
        int i = 0;
        while (i < sizeOfBoard) {
            btnNorth[i] = new JButton("Up");
            btnNorth[i].setBackground(Color.LIGHT_GRAY);
            btnNorth[i].addActionListener(new Listener1(i));
            NorthButton.add(btnNorth[i]);
            i++;
        }
        add(BorderLayout.SOUTH, NorthButton);

        downButton = new JPanel();
        downButton.setLayout(new GridLayout(1, sizeOfBoard, 2, 2));
        i = 0;
        while (i < sizeOfBoard) {
            btnSouth[i] = new JButton("Down");
            btnSouth[i].setBackground(Color.LIGHT_GRAY);
            btnSouth[i].addActionListener(new Listener2(i));
            downButton.add(btnSouth[i]);
            i++;
        }
        add(BorderLayout.NORTH, downButton);

        rightButton = new JPanel();
        rightButton.setLayout(new GridLayout(sizeOfBoard, 1, 2, 2));
        i = 0;
        while (i < sizeOfBoard) {
            btnEast[i] = new JButton("Left");
            btnEast[i].setBackground(Color.LIGHT_GRAY);
            btnEast[i].addActionListener(new Listener3(i));
            rightButton.add(btnEast[i]);
            i++;
        }
        add(BorderLayout.WEST, rightButton);

        leftButton = new JPanel();
        leftButton.setLayout(new GridLayout(sizeOfBoard, 1, 2, 2));
        i = 0;
        while (i < sizeOfBoard) {
            btnWets[i] = new JButton("Right");
            btnWets[i].setBackground(Color.LIGHT_GRAY);
            btnWets[i].addActionListener(new Listener4(i));
            leftButton.add(btnWets[i]);
            i++;
        }
        add(BorderLayout.EAST, leftButton);

        CreateColors();
    }
//+
    static int[] returnRandNums(int a, int z) {
        int n = z - a + 1;
        int i;
        int numb[] = new int[n];
        i = 0;
        while (i < n) {
            numb[i] = i;
            i++;
        }
        int res[] = new int[n];
        int x = n;
        SecureRandom rd = new SecureRandom();
        i = 0;
        while (i < n) {
            int kf = rd.nextInt(x);
            res[i] = numb[kf];
            numb[kf] = numb[x - 1];
            x--;
            i++;
        }
        return res;
    }
//++
    private void CreateColors() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(sizeOfBoard, sizeOfBoard, 2, 2));
        for (int i = 0; i < sizeOfBoard; i++) {
            int num[] = returnRandNums(0, sizeOfBoard - 1);
            for (int j = 0; j < sizeOfBoard; j++) {
                p[i][j] = new JLabel();
                p[i][j].setOpaque(true);
                
                switch(num[j]){
                    case(0):
                    p[i][j].setBackground(Color.BLACK);
                    break;
                    case(1):
                    p[i][j].setBackground(Color.RED);
                    break;

                    case(2):
                    p[i][j].setBackground(Color.YELLOW);
                    break;
                    case(3):
                    p[i][j].setBackground(Color.BLUE);
                    break;
                    case(4):
                    p[i][j].setBackground(Color.ORANGE);
                    break;
                    case(5):
                    p[i][j].setBackground(Color.GREEN);
                    break;


            
            
            }
                panel.add(p[i][j]);
            }
        }
        add(BorderLayout.CENTER, panel);
    }
//++
    private void ShuffleOnBoard() {
        int i = 0;
        while (i < sizeOfBoard) {
            int num[] = returnRandNums(0, sizeOfBoard - 1);
            int j = 0;
            while (j < sizeOfBoard) {
                switch(num[j]){
                    case(0):
                    p[i][j].setBackground(Color.BLACK);
                    break;
                    case(1):
                    p[i][j].setBackground(Color.RED);
                    break;

                    case(2):
                    p[i][j].setBackground(Color.YELLOW);
                    break;
                    case(3):
                    p[i][j].setBackground(Color.BLUE);
                    break;
                    case(4):
                    p[i][j].setBackground(Color.ORANGE);
                    break;
                    case(5):
                    p[i][j].setBackground(Color.GREEN);
                    break;


            
            
            }
                j++;
            }
            i++;
        }
    }
//////////////


//++
    private void checkSize2() {
        int i=0;
        boolean isTrue[] = new boolean[2];
        Arrays.fill(isTrue, false);

        while( i < 2) {
            if (p[i][0].getBackground() == p[i][1].getBackground()
                    || p[0][i].getBackground() == p[1][i].getBackground()) {
                        isTrue[i] = true;
            }
            i++;
        }
        if (isTrue[0] == isTrue[1] && isTrue[1] == true) {
            JOptionPane.showMessageDialog(null, "You  Win!   Count of steps: " + cnt);
            ShuffleOnBoard();
            cnt = 0;
        }
    }
//++
    private void checkSize4() {
        int i=0;

        boolean isTrue[] = new boolean[4];
        Arrays.fill(isTrue, false);

        while( i < 4) {
            if (p[i][0].getBackground() == p[i][1].getBackground()
                    && p[i][1].getBackground() == p[i][2].getBackground()
                    && p[i][2].getBackground() == p[i][3].getBackground()
                    || p[0][i].getBackground() == p[1][i].getBackground()
                            && p[1][i].getBackground() == p[2][i].getBackground()
                            && p[2][i].getBackground() == p[3][i].getBackground()) {
                                isTrue[i] = true;
            }
            i++;
        }
        if (isTrue[0] == isTrue[1] && isTrue[1] == isTrue[2] && isTrue[2] == isTrue[3] && isTrue[3] == true) {
            JOptionPane.showMessageDialog(null, "You Win!   Count of steps: " + cnt);
            cnt = 0;
            ShuffleOnBoard();

        }
    }
//++
    private void checkSize6() {
        int i=0;
        boolean isTrue[] = new boolean[6];
        Arrays.fill(isTrue, false);

        while( i < 6) {

            if (p[i][0].getBackground() == p[i][1].getBackground()
                    && p[i][1].getBackground() == p[i][2].getBackground()
                    && p[i][2].getBackground() == p[i][3].getBackground()
                    && p[i][3].getBackground() == p[i][4].getBackground()
                    && p[i][4].getBackground() == p[i][5].getBackground()
                    || p[0][i].getBackground() == p[1][i].getBackground()
                            && p[1][i].getBackground() == p[2][i].getBackground()
                            && p[2][i].getBackground() == p[3][i].getBackground()
                            && p[3][i].getBackground() == p[4][i].getBackground()
                            && p[4][i].getBackground() == p[5][i].getBackground())
                            isTrue[i] = true;
            i++;
        }
        if (isTrue[0] == isTrue[1] && isTrue[1] == isTrue[2] && isTrue[2] == isTrue[3] && isTrue[3] == isTrue[4] && isTrue[4] == isTrue[5] && isTrue[5] == true) {
            JOptionPane.showMessageDialog(null, "You  Win!   Count of steps: " + cnt);
            ShuffleOnBoard();
            cnt = 0;
        }
    }
//++
    public void CheckWin(int n) {
        int sob = n;
        switch (sob) {
            case (6):
                checkSize6();
            case (4):
                checkSize4();
            case (2):
                checkSize2();

        }

    }
/////////

    class Listener1 implements ActionListener {
        private final int btn;

        public Listener1(int i) {
            this.btn = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cnt++;
            Color a;
            a = p[sizeOfBoard - 1][btn].getBackground();
            int i=sizeOfBoard - 1;
            while ( i > 0 ) {
                p[i][btn].setBackground(p[i - 1][btn].getBackground());
                i--;
            }
            p[0][btn].setBackground(a);
            CheckWin(sizeOfBoard);
        }
    }

    class Listener2 implements ActionListener {
        private final int btn;

        public Listener2(int i) {
            this.btn = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cnt++;
            Color a;
            a = p[0][btn].getBackground();
            for (int i = 0; i < sizeOfBoard - 1; i++) {
                p[i][btn].setBackground(p[i + 1][btn].getBackground());
            }
            p[sizeOfBoard - 1][btn].setBackground(a);

            CheckWin(sizeOfBoard);

        }
    }

    class Listener3 implements ActionListener {
        private final int btn;

        public Listener3(int i) {
            this.btn = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cnt++;
            Color a;
            a = p[btn][0].getBackground();
            for (int i = 0; i < sizeOfBoard - 1; i++) {
                p[btn][i].setBackground(p[btn][i + 1].getBackground());
            }
            p[btn][sizeOfBoard - 1].setBackground(a);
            CheckWin(sizeOfBoard);

        }
    }

    class Listener4 implements ActionListener {
        private final int btn;

        public Listener4(int i) {
            this.btn = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cnt++;
            Color a;
            a = p[btn][sizeOfBoard - 1].getBackground();
            for (int i = sizeOfBoard - 1; i > 0; i--) {
                p[btn][i].setBackground(p[btn][i - 1].getBackground());
            }
            p[btn][0].setBackground(a);
            switch (sizeOfBoard) {
                case (6):
                    checkSize6();
                case (4):
                    checkSize4();
                case (2):
                    checkSize2();

            }
        }
    }

}
