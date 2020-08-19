package Guest;
import Guest.Controller;

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
                str += "Дату заселения и выезда нужно вводить в формате yyyy-[m]m-[d]d \n";
                str += "Дата заселения не должна быть позже даты выезда. \n";
                str += "Долг должен принимать вещественное значение. Если долга нет, можно указать 0.\n";
                str += "Нельзя заселять человека в занятый номер.\n";
            }
            case "params":{
                str += "Вводимые организация и гость должны быть записаны как в базе данных.\n";
            }
            case "settle":{
                str += "Дату заселения и выезда нужно вводить в формате yyyy-[m]m-[d]d \n";
                str += "Дата заселения не должна быть позже даты выезда. \n";
                str += "Нельзя заселять человека в занятый номер.\n";
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
