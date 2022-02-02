package lesson14;

import javax.swing.*;
import java.awt.*;

public class Animate {
    int x = 1;
    int y = 1;

    public static void main(String[] args) {
        new Animate().go();
    }

    public void go(){
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var myDraw = new MyDrawP();
        frame.getContentPane().add(myDraw);

        frame.setSize(520,280);
        frame.setVisible(true);

        for (int i = 0; i < 124; i++, x++, y++) {
            myDraw.repaint();
            try {
                Thread.sleep(50);
            } catch (Exception e) {}
        }
    }

    class MyDrawP extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.blue);
            g.fillRect(x, y, 500 - x * 2, 250 - y * 2);

        }
    }

}
