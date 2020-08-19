package AboutRoom.Parameters;

import AboutRoom.Controller;
import AboutRoom.HelpNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterParameters extends JFrame {
    Controller controller;
    String corpusName;
    private final JLabel popular = new JLabel("");
    private final JLabel count = new JLabel("");
    private final JLabel cost = new JLabel("");

    public EnterParameters(Controller controller_, String corpusName_) {
        controller = controller_;
        corpusName = corpusName_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Введите популярность (вещественное число больше 0)");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        JTextField jTextField1 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField1, gbc);


        JLabel textTop2 = new JLabel("Введите местность (целое от 1 до 5)");
        textTop2.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop2, gbc);

        JTextField jTextField2 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField2, gbc);


        JLabel textTop3 = new JLabel("Введите цену (вещественное число больше 0)");
        textTop3.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop3, gbc);

        JTextField jTextField3 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField3, gbc);

        //запомнить выбор для jTextField
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                popular.setText(text);
            }
        };
        jTextField1.addActionListener(actionListenerComboBox);

        ActionListener actionListenerComboBox2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                count.setText(text);
            }
        };
        jTextField2.addActionListener(actionListenerComboBox2);

        ActionListener actionListenerComboBox3 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                cost.setText(text);
            }
        };
        jTextField3.addActionListener(actionListenerComboBox3);

        JButton button3 = new JButton("найти номера");
        addActionListenerSaveButton(button3);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(button3, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerSaveButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(correctData()) {
                    new List(controller, corpusName, popular.getText(), count.getText(), cost.getText());
                } else {
                    new HelpNote("period");
                }
                dispose();
            }
        });
    }

    private boolean correctData() {
        try {
           Double.valueOf(popular.getText());
           Double.valueOf(cost.getText());
            Integer tmp = Integer.valueOf(count.getText());
            if(tmp > 5) return false;
            if(tmp <= 0) return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}