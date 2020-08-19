package Guest.Find;

import Guest.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddService extends JFrame {
    Controller controller;
    String[] dataGuest;
    private final JLabel select = new JLabel(" ");

    public AddService(Controller controller_, String[] dataGuest_) {
        controller = controller_;
        dataGuest = dataGuest_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        ////показать список всех услуг
        JLabel textTop = new JLabel("Выберите услугу");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        String[] services = controller.getAllService();
        JComboBox jComboBox = new JComboBox(services);
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

        JButton selectButton = new JButton("Выбрать");
        addAction(selectButton);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(selectButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addAction(JButton button) {
        button.addActionListener(new ActionListener() {
                                     public void actionPerformed(ActionEvent e) {
                                         controller.addCredit(select.getText(), dataGuest);
                                         dispose();
                                     }
                                 }

        );
    }

}