package AboutRoom.Date;

import AboutRoom.Controller;
import AboutRoom.HelpNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class EnterDate extends JFrame {
    Controller controller;
    String corpusName;
    private final JLabel date = new JLabel("");

    public EnterDate(Controller controller_, String corpusName_) {
        corpusName = corpusName_;
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Введите дату в формате [yyyy]-[mm]-[dd]");
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
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField1, gbc);

        //запомнить выбор для jTextField
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                date.setText(text);
            }
        };
        jTextField1.addActionListener(actionListenerComboBox);

        JButton button3 = new JButton("далее");
        addActionListenerSaveButton(button3);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(button3, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerSaveButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(correctData()) {
                    new List(controller, corpusName, date.getText());
                } else {
                    new HelpNote("period");
                }
                dispose();
            }
        });
    }

    private boolean correctData() {
        try {
            Date.valueOf(date.getText());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}