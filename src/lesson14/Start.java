package lesson14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;

public class Start {

    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        var button1 = new JButton("Поменять цвет");
        final var panel = new DrawPanel();
        button1.addActionListener(e -> {
            panel.repaint();
        });
        frame.getContentPane().add(BorderLayout.SOUTH, button1);
        frame.getContentPane().add(BorderLayout.CENTER, panel);

        final var text = new JLabel("это число 2");
        var button2 = new JButton("Добавить число!");

        button2.addActionListener(e -> {
            var ss = text.getText().split(" ");
            text.setText(ss[0] + " " + ss[1] + " " + (Integer.parseInt(ss[2]) + 1));
        });

        frame.getContentPane().add(BorderLayout.EAST, button2);
        frame.getContentPane().add(BorderLayout.WEST, text);


        frame.setSize(400, 400);
        frame.setVisible(true);


    }
}

class DrawPanel extends JPanel {
    Random random = new Random();
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(random.nextInt()));
        g.fillOval(30,30,150,150);
    }
}
