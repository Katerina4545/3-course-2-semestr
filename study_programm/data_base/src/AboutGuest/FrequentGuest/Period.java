package AboutGuest.FrequentGuest;

import AboutGuest.Controller;
import AboutGuest.HelpNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class Period extends JFrame {
    Controller controller;
    private final JLabel start = new JLabel("");
    private final JLabel finish = new JLabel("");

    public Period(Controller controller_, String corpus) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel textTop = new JLabel("Введите период или оставьте поля пустыми");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        JTextField jTextField1 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField1, gbc);

        JTextField jTextField2 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField2, gbc);

        //запомнить выбор для jTextField
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                start.setText(text);
            }
        };
        jTextField1.addActionListener(actionListenerComboBox);

        ActionListener actionListenerComboBox2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                finish.setText(text);
            }
        };
        jTextField2.addActionListener(actionListenerComboBox2);

        JButton nextButton = new JButton("Далее");
        addActionListenerNextButton(nextButton, corpus);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(nextButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerNextButton(JButton button, String corpus) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(start.getText().equals("") || finish.getText().equals("")) {
                    new List(controller, corpus, start.getText(), finish.getText(), true);
                    dispose();
                } else{
                    if(correct()) {
                        new List(controller, corpus, start.getText(), finish.getText(), false);
                        dispose();
                    }
                    else new HelpNote("period");
                }
            }
        });
    }

    private boolean correct(){
        try {
            Date st = Date.valueOf(start.getText());
            Date fn = Date.valueOf(finish.getText());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}