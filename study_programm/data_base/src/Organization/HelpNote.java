package Organization;


import javax.swing.*;
import java.awt.*;

public class HelpNote extends JFrame{

    public HelpNote(String note){
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String str = "Вы ввели неверные данные.\nОзнакомьтесь с инструкцией ниже.\n\n";

        switch (note){
            case "add":{
                str += "После каждого ввода нажимайте enter.\n";
                str += "Название организации должно быть в одинарных кавычках.\n";
                str += "Дату начала сотрудничества и конца нужно вводить в формате yyyy-[m]m-[d]d \n";
                str += "Дата начала сотрудничества не должна быть позже даты конца. \n";
            }
            case "period":{
                str += "После каждого ввода нажимайте enter.\n";
                str += "Даты нужно вводить в формате yyyy-[m]m-[d]d \n";
                str += "Первая дата не должна быть позже второй даты. \n";
            }
            case "count":{
                str += "После каждого ввода нажимайте enter.\n";
                str += "Число мест должно быть неотрицательным целым числом.\n";
            }
            case "stat":{
                str += "После каждого ввода нажимайте enter.\n";
                str += "Организация должна быть в базе данных.\n";
                str += "название организации должно быть в двойных кавычках.\n";
                str += "Даты нужно вводить в формате yyyy-[m]m-[d]d \n";
                str += "Первая дата не должна быть позже второй даты. \n";
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

