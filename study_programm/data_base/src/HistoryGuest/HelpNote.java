package HistoryGuest;

import javax.swing.*;
import java.awt.*;

public class HelpNote extends JFrame {

    public HelpNote(String note){
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String str = "Вы ввели неверные данные.\nОзнакомьтесь с инструкцией ниже.\n\n";

        switch (note){
            case "period":{
                str += "Дату заселения и выезда нужно вводить в формате yyyy-[m]m-[d]d \n";
                str += "Дата заселения не должна быть позже даты выезда. \n";
            }
            case "jComboBox":{
                str += "Необходимо выбрать непустое поле.\n";
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
