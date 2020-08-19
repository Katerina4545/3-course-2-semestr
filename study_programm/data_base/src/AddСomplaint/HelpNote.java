package AddСomplaint;

import javax.swing.*;
import java.awt.*;

public class HelpNote extends JFrame {
    Controller controller;
    public HelpNote(Controller controller_, String note){
        controller=controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String str = "Вы ввели неверные данные.\nОзнакомьтесь с инструкцией ниже.\n\n";

        switch (note){
            case "add":{
                str += "Организация, корпус и номер должны быть в базе данных.\n";
                str += "Описание не может быть пустым.\n";
            }
        }

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
