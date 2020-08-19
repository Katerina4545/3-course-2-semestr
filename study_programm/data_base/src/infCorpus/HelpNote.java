package infCorpus;

import javax.swing.*;
import java.awt.*;

public class HelpNote extends JFrame {
    public HelpNote(Integer maxNumber){
        setSize(500, 200);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //заголовок
        JLabel text1 = new JLabel("Вы ввели данные в некорректном формате.");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(text1, gbc);

        JLabel text2 = new JLabel("Прочитайте ограничения на поля и повторите попытку.");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(text2, gbc);

        JLabel text3 = new JLabel("Номер должен быть больше "+ maxNumber.toString() + ".");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(text3, gbc);

        JLabel text4 = new JLabel("Название должно не превышать 50 символов.");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(text4, gbc);

        JLabel text5 = new JLabel("Класс отеля принимает значения от 1 до 5.");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(text5, gbc);

        JLabel text6 = new JLabel("Количество этажей и комнат есть неотрицательное целое число больше 1.");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(text6, gbc);


        add(panel);
        setVisible(true);
    }
}
