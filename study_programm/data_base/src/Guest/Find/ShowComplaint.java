package Guest.Find;

import Guest.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowComplaint extends JFrame {
    Controller controller;

    public ShowComplaint(Controller controller_, Integer idGuest) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] list = controller.getGuestCompl(idGuest);

        JLabel textTop = new JLabel("Список жалоб");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(textTop, gbc);

        JTextArea jTextArea = new JTextArea(8, 10);
        for(int i=0; i<list.length;i++){
            jTextArea.setText(list[i]);
        }
        jTextArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(new JScrollPane(jTextArea), gbc);

        add(panel);
        setVisible(true);
    }
}