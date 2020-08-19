package CurrentComplaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowComplaint extends JFrame {
    Controller controller;
    private final JLabel select = new JLabel(" ");

    public ShowComplaint(Controller controller_, String corpus) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] complaints = controller.getComplaints(corpus);
        JComboBox jComboBox = new JComboBox(complaints);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 40, 0);
        panel.add(jComboBox, gbc);

        JButton selectButton = new JButton("Подробнее");
        addActionListenerSelectButton(selectButton, corpus);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(selectButton, gbc);

        //запомнить выбор для jcombobox
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                String text = (String) box.getSelectedItem();
                select.setText(text);
            }
        };
        jComboBox.addActionListener(actionListenerComboBox);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerSelectButton(JButton button, String corpus) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ShowInf(controller, select.getText());
            }
        });
    }

}