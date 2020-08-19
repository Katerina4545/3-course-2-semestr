package Organization.AllStat;

import Organization.Controller;
import Organization.HelpNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountBooking extends JFrame{
    private final JLabel count = new JLabel("");
    Controller controller;

    public CountBooking(Controller controller_) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Введите число мест");
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

        //запомнить выбор для jTextField
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                count.setText(text);
            }
        };
        jTextField1.addActionListener(actionListenerComboBox);

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
                if(correctNumber(count.getText())) {
                    controller.saveCount(Integer.valueOf(count.getText()));
                    new List(controller);
                }else new HelpNote("count");
                dispose();
            }
        });
    }

    private boolean correctNumber(String str){
        try {
            Integer number = Integer.valueOf(str);
            if(number < 0) return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
