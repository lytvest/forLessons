package lesson16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private static double old;
    private static String operation = "+";
    public static void main(String[] args) {
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        var con = new GridBagConstraints();
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 2;
        con.weighty = 2;
        con.insets = new Insets(10,10,10,10);

        var field = new JTextField(50);
        con.gridwidth = 3;
        panel.add(field, con);
        con.gridwidth = 1;
        con.gridx = 3;
        createButton(panel, con, "C", e -> field.setText(""));

        con.gridy = 1;
        con.gridx = 0;
        createButton(panel, con, "1", addIntListener("1", field));
        con.gridx = 1;
        createButton(panel, con, "2", addIntListener("2", field));
        con.gridx = 2;
        createButton(panel, con, "3", addIntListener("3", field));
        con.gridx = 3;
        createButton(panel, con, "*", addOperatorListener("*",field));

        con.gridy = 2;
        con.gridx = 0;
        createButton(panel, con, "4", addIntListener("4", field));
        con.gridx = 1;
        createButton(panel, con, "5", addIntListener("5", field));
        con.gridx = 2;
        createButton(panel, con, "6", addIntListener("6", field));
        con.gridx = 3;
        createButton(panel, con, "/", addOperatorListener("/", field));

        con.gridy = 3;
        con.gridx = 0;
        createButton(panel, con, "7", addIntListener("7", field));
        con.gridx = 1;
        createButton(panel, con, "8", addIntListener("8", field));
        con.gridx = 2;
        createButton(panel, con, "9", addIntListener("9", field));
        con.gridx = 3;
        createButton(panel, con, "-", addOperatorListener("-", field));

        con.gridy = 4;
        con.gridx = 0;
        createButton(panel, con, ".", addIntListener(".", field));
        con.gridx = 1;
        createButton(panel, con, "0", addIntListener("0", field));
        con.gridx = 2;
        createButton(panel, con, "=", addCalculateListener(field));
        con.gridx = 3;
        createButton(panel, con, "+", addOperatorListener("+", field));

        var frame = new JFrame("Калькулятор");
        frame.getContentPane().add(panel);
        frame.setBounds(1200,400,400,400);
        frame.setVisible(true);
    }
    private static ActionListener addIntListener(String s, JTextField field){
        return e -> field.setText(field.getText() + s);
    }
    private static ActionListener addOperatorListener(String oper, JTextField field){
        return e -> {
            operation = oper;
            old = Double.parseDouble(field.getText());
            field.setText("");
        };
    }
    private static ActionListener addCalculateListener(JTextField field){
        return e -> {
            double num = Double.parseDouble(field.getText());
            switch (operation) {
                case "+" -> field.setText("" + (num + old));
                case "-" -> field.setText("" + (old - num));
                case "*" -> field.setText("" + (num * old));
                case "/" -> {
                    if (num == 0.0)
                        field.setText("Делить на 0 нельзя!");
                    else
                        field.setText("" + (old / num));
                }
            }
            old = num;
        };
    }

    private static void createButton(JPanel panel, GridBagConstraints con, String text, ActionListener listener) {
        var button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button, con);
    }


}
