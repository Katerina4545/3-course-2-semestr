package Booking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete extends JFrame {
    Controller controller;
    private final JLabel select = new JLabel(" ");
    public Delete(Controller controller_){
        controller=controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Выберите бронь для удаления");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        String[] simpleBookingData = controller.getSimpleBookingData();
        JComboBox jComboBox = new JComboBox(simpleBookingData);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 40, 0);
        panel.add(jComboBox, gbc);

        //запомнить выбор для jcombobox
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                String text = (String) box.getSelectedItem();
                select.setText(text);
            }
        };
        jComboBox.addActionListener(actionListenerComboBox);

        JButton deleteButton = new JButton("Удалить");
        addActionListenerDeleteButton(deleteButton);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(deleteButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerDeleteButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.deleteBooking(select.getText());
                dispose();
            }
        });
    }
}
