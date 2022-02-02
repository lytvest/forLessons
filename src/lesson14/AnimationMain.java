package lesson14;

import javax.swing.*;
import java.awt.*;

public class AnimationMain {
    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var animation = new AnimationPanel();
        frame.getContentPane().add(animation);
        frame.setSize(400, 400);
        frame.setVisible(true);

        for(int i = 0; i < 200; i++){
            animation.animate();
            try {
                Thread.sleep(50);
            } catch (Exception e) {}
        }
    }
}

class AnimationPanel extends JPanel {
    int x = 10, y = 10;
    int ex = 150, ey = 150;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.orange);
        g.fillOval(x,y,70,70);
    }

    void animate(){
        x += 1;
        y += 1;
        if (x > ex) x = ex;
        if (y > ey) y = ey;
        repaint();
    }
}
