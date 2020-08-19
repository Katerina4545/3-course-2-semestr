package infRoom;

import javax.swing.*;
import java.awt.*;

public class HelpNote extends JFrame {
        public HelpNote(String note){

                setSize(500, 500);
                setLocationRelativeTo(null);
                JPanel panel = new JPanel();
                panel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                String str = "Вы ввели неверные данные.\nОзнакомьтесь с инструкцией ниже.\n\n";

                switch (note){
                        case "period":{
                                str += "Введите корректную дату в формате \nхх.хх.хххх";
                        }
                        case "edit":{
                                str += "Номер комнаты должен быть\n" +
                                        "целочисленным трехзначным числом.\n" +
                                        "1ая цифра означает этаж, \n" +
                                        "две последние номер на этаже.\n";
                                str += "Занятость может принимать значения\n yes или no.\n";
                                str += "Популярность должна быть меньше 100.\n";
                                str += "Вместимость не более 5.\n";
                                str += "Цена - вещественное число.\n";
                        }
                        case "create":{
                                str += "Номер комнаты должен быть\n" +
                                        "целочисленным трехзначным числом.\n" +
                                        "1ая цифра означает этаж, \n" +
                                        "две последние номер на этаже.\n";
                                str += "Занятость может принимать значения\n yes или no.\n";
                                str += "Популярность должна быть меньше 100.\n";
                                str += "Вместимость не более 5.\n";
                                str += "Цена - вещественное число.\n";
                        }
                }

                JTextArea jTextArea = new JTextArea(20, 25);
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
