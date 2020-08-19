package Guest.Find;

import Guest.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Evict extends JFrame {
    Controller controller;

    //0ID_ЗАПИСЬ	1ID_ПОСТОЯЛЕЦ	2ID_КОРПУС	3ID_НОМЕР	4ДАТА_ЗАСЕЛ	5ДАТА_ВЫЕЗД	6ДОЛГ
    public Evict(Controller controller_, String[] dataGuest) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        controller.evict(Integer.valueOf(dataGuest[2]), Integer.valueOf(dataGuest[3]));

        JLabel textTop = new JLabel("Номер успешно освобожден!");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        add(panel);
        setVisible(true);
    }
}
