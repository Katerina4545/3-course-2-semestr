package CurrentComplaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Change extends JFrame {
    Controller controller;
    private final JLabel select = new JLabel(" ");

    public Change(Controller controller_, String complaint) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Выберите статус");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        JComboBox jComboBox = new JComboBox(new String[]{"на рассмотрении", "обрабатывается", "исправлено"});
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 40, 0);
        panel.add(jComboBox, gbc);

        JButton selectButton = new JButton("Изменить");
        addActionListenerSelectButton(selectButton, complaint);
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

    private void addActionListenerSelectButton(JButton button, String complaint) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (select.getText()){
                    case "на рассмотрении":{
                        controller.changeCompl(complaint, 1);
                        break;
                    }
                    case "обрабатывается":{
                        controller.changeCompl(complaint, 2);
                        break;
                    }
                    case "исправлено":{
                        controller.changeCompl(complaint, 3);
                        break;
                    }
                }
                dispose();
            }
        });
    }

}