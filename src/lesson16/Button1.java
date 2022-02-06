package lesson16;

import javax.swing.*;
import java.awt.*;

public class Button1 {

    public static void main(String[] args) {
        var frame = new JFrame();
        frame.setSize(300,300);
        frame.getContentPane().add(BorderLayout.NORTH, new JButton(BorderLayout.NORTH));
        frame.getContentPane().add(BorderLayout.SOUTH, new JButton(BorderLayout.SOUTH));
        frame.getContentPane().add(BorderLayout.CENTER, new JButton(BorderLayout.CENTER));
        frame.getContentPane().add(BorderLayout.EAST, new JButton(BorderLayout.EAST));
        frame.getContentPane().add(BorderLayout.WEST, new JButton(BorderLayout.WEST));
        frame.setVisible(true);
    }
}
