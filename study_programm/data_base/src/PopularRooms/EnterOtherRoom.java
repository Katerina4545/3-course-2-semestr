package PopularRooms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterOtherRoom extends JFrame {
    Controller controller;
    private final JLabel start = new JLabel("");

    public EnterOtherRoom(Controller controller_, String corpus) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Введите номер");
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

        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                start.setText(text);
            }
        };
        jTextField1.addActionListener(actionListenerComboBox);

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
                if (correct()) {
                    new OtherRoomInf(controller, corpus, start.getText());
                } else {
                    new HelpNote("room");
                }
            }
        });
    }

    private boolean correct(){
        try {
            int i = Integer.valueOf(start.getText());
            if (i <= 0) return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}