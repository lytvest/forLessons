package lesson16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextArea1 {
    public static void main(String[] args) {
        var frame = new JFrame();
        var panel = new JPanel();
        var button = new JButton("cleck me!");
        var text = new JTextArea(10, 20);
        text.setLineWrap(true);

        var scrollPane = new JScrollPane(text);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        button.addActionListener(e -> text.append("button clicked \n"));

        frame.setBounds(1200,400,300,300);
        frame.setVisible(true);

    }
}
