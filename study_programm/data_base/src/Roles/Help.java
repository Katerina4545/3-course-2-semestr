package Roles;

import javax.swing.*;
import java.awt.*;

public class Help extends JFrame {
    public Help(){
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String str = "Вы ввели неверные данные, либо такого пользователя нет.\n\n";

        JTextArea jTextArea = new JTextArea(20, 40);
        jTextArea.setText(str);
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
