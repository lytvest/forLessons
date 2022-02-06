package lesson16;

import javax.swing.*;
import java.awt.*;

public class Panel1 {
    public static void main(String[] args) {
        var frame = new JFrame();
        var panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(BorderLayout.EAST, panel);

        panel.add(new JButton("click me"));
        panel.add(new JButton("click me 2"));
        frame.setBounds(1200,400,300,300);
        frame.setVisible(true);

    }
}
