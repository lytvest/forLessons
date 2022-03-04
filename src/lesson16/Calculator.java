package lesson16;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        con.insets = new Insets(10, 10, 10, 10);

        var field = new JTextField(50);
        con.gridwidth = 3;
        panel.add(field, con);
        con.gridwidth = 1;
        con.gridx = 3;
        createButton(panel, con, "C", e -> field.setText(""));

        con.gridy = 1;
        con.gridx = 0;
        createButton(panel, con, "1", addSymbolListener("1", field));
        con.gridx = 1;
        createButton(panel, con, "2", addSymbolListener("2", field));
        con.gridx = 2;
        createButton(panel, con, "3", addSymbolListener("3", field));
        con.gridx = 3;
        createButton(panel, con, "*", addSymbolListener("*", field));

        con.gridy = 2;
        con.gridx = 0;
        createButton(panel, con, "4", addSymbolListener("4", field));
        con.gridx = 1;
        createButton(panel, con, "5", addSymbolListener("5", field));
        con.gridx = 2;
        createButton(panel, con, "6", addSymbolListener("6", field));
        con.gridx = 3;
        createButton(panel, con, "/", addSymbolListener("/", field));

        con.gridy = 3;
        con.gridx = 0;
        createButton(panel, con, "7", addSymbolListener("7", field));
        con.gridx = 1;
        createButton(panel, con, "8", addSymbolListener("8", field));
        con.gridx = 2;
        createButton(panel, con, "9", addSymbolListener("9", field));
        con.gridx = 3;
        createButton(panel, con, "-", addSymbolListener("-", field));

        con.gridy = 4;
        con.gridx = 0;
        createButton(panel, con, ".", addSymbolListener(".", field));
        con.gridx = 1;
        createButton(panel, con, "0", addSymbolListener("0", field));
        con.gridx = 2;
        createButton(panel, con, "=", addCalculateListener(field));
        con.gridx = 3;
        createButton(panel, con, "+", addSymbolListener("+", field));

        con.gridy = 5;
        con.gridx = 0;
        createButton(panel, con, "(", addSymbolListener("(", field));
        con.gridx = 1;
        createButton(panel, con, ")", addSymbolListener(")", field));
        con.gridx = 2;
        createButton(panel, con, "m+", e -> {
            try {
                mem += parse(field.getText());
            } catch (Exception ex) {
                field.setText("Не корректрный данные");
            }
        });
        con.gridx = 3;
        createButton(panel, con, "m-",  e -> {
            try {
                mem -= parse(field.getText());
            } catch (Exception ex) {
                field.setText("Не корректрный данные");
            }
        });
        con.gridy = 6;
        con.gridx = 0;
        createButton(panel, con, "m", e -> {
            field.setText("" + mem);
        });
        con.gridx = 1;
        createButton(panel, con, "me", e -> {
            mem = 0;
        });


        var frame = new JFrame("Калькулятор");
        frame.getContentPane().add(panel);
        frame.setBounds(1200, 400, 400, 400);
        frame.setVisible(true);
    }

    private static double mem = 0;

    private static ActionListener addSymbolListener(String s, JTextField field) {
        return e -> field.setText(field.getText() + s);
    }

    private static ActionListener addOperatorListener(String oper, JTextField field) {
        return e -> {
            operation = oper;
            old = Double.parseDouble(field.getText());
            field.setText("");
        };
    }

    static double parse(String str) {
        str = "(" + str + ")";
        var sb = new StringBuilder();
        var stack = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            var ch = str.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                sb.append(ch);
            } else {
                if (!sb.isEmpty()) {
                    stack.add(Double.parseDouble(sb.toString()));
                    sb = new StringBuilder();
                }
                if (ch == ')') {
                    var left = 0;
                    for (int j = stack.size() - 1; j >= 0; j--) {
                        if (stack.get(j).equals("(")) {
                            left = j;
                            break;
                        }
                    }
                    for (int j = left + 2; j < stack.size() - 1; j++) {
                        if (stack.get(j).equals("*")) {
                            var res = ((Double) stack.get(j - 1)) * ((Double) stack.get(j + 1));
                            removeFromStack(stack, j, res);
                            j--;
                            continue;
                        }
                        if (stack.get(j).equals("/")) {
                            var res = ((Double) stack.get(j - 1)) / ((Double) stack.get(j + 1));
                            removeFromStack(stack, j, res);
                            j--;
                        }
                    }
                    for (int j = left + 2; j < stack.size() - 1; j++) {
                        if (stack.get(j).equals("+")) {
                            var res = ((Double) stack.get(j - 1)) + ((Double) stack.get(j + 1));
                            removeFromStack(stack, j, res);
                            j--;
                            continue;
                        }
                        if (stack.get(j).equals("-")) {
                            var res = ((Double) stack.get(j - 1)) - ((Double) stack.get(j + 1));
                            removeFromStack(stack, j, res);
                            j--;
                        }
                    }
                    stack.remove(left);
                } else {
                    stack.add("" + ch);
                }
            }
        }
        return (Double) stack.get(0);
    }

    private static void removeFromStack(ArrayList<Object> stack, int j, double res) {
        stack.remove(j - 1);
        stack.remove(j - 1);
        stack.remove(j - 1);
        stack.add(j - 1, res);
        for (var e : stack) {
            System.out.print(e + " ");
        }
    }

    private static ActionListener addCalculateListener(JTextField field) {
        return e -> {
            try {
                field.setText("" + parse(field.getText()));
            } catch (Exception ex) {
                field.setText("Не корректрный данные");
            }
        };
    }

    private static void createButton(JPanel panel, GridBagConstraints con, String text, ActionListener listener) {
        var button = new JButton(text);
        button.addActionListener(listener);
        panel.add(button, con);
    }


}
