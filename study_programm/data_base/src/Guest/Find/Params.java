package Guest.Find;

import Guest.Controller;
import Guest.HelpNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Params extends JFrame {
    Controller controller;
    private final JLabel org = new JLabel("");
    private final JLabel name = new JLabel("");

    public Params(Controller controller_) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Введите название организации и ФИО");
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
                org.setText(text);
            }
        };
        jTextField1.addActionListener(actionListenerComboBox);

        ActionListener actionListenerComboBox2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                name.setText(text);
            }
        };
        jTextField2.addActionListener(actionListenerComboBox2);

        JButton nextButton = new JButton("Далее");
        addActionListenerNextButton(nextButton);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(nextButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerNextButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(controller.organizationExist(org.getText()))
                    new GuestInf(controller, org.getText(), name.getText());
                else new HelpNote(controller, "params");
                dispose();
            }
        });
    }

}
