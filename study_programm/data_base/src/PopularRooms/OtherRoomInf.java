package PopularRooms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtherRoomInf extends JFrame {
    Controller controller;

    public OtherRoomInf(Controller controller_, String corpus, String room) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String data = controller.getInfRoom(corpus, room);

        JLabel textTop = new JLabel("информация о номере:");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        String str1 = "номер - " + room;
        JLabel text1 = new JLabel(str1);
        text1.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(text1, gbc);

        String str2 = "популярность - " + data;
        JLabel text2 = new JLabel(str2);
        text2.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(text2, gbc);

        add(panel);
        setVisible(true);
    }

}