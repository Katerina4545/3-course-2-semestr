package Booking;

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
                str += "Организация должна быть в базе данных. Иначе создайте новую организацию.\n";
                str += "Количество человек и комнат должно принимать целочисленное значение. \n";
                str += "Поле \"Желаемый этаж\" должно принимать целочисленное значение. \n";
                str += "Дату заселения и выезда нужно вводить в формате yyyy-[m]m-[d]d \n";
                str += "Дата заселения не должна быть позже даты выезда. \n";
                str += "Скидка и стоимость должны принимать вещественное значение. \n";
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
