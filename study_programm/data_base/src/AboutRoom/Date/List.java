package AboutRoom.Date;

import AboutRoom.Controller;

import javax.swing.*;
import java.awt.*;

public class List extends JFrame {
    Controller controller;

    public List(Controller controller_, String corpusName, String date) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] data = controller.getDateList(corpusName, date);

        JTextArea jTextArea = new JTextArea(8, 10);
        int i=0;
        while (i<data.length){
            if(data[i] != null)
            jTextArea.setText(data[i]);
            i++;
        }
        jTextArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(new JScrollPane(jTextArea), gbc);

        add(panel);
        setVisible(true);
    }
}
