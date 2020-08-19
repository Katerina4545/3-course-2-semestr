package Organization.Stat;

import Organization.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewResult extends JFrame{

    public ViewResult(Controller controller) {
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        if(controller.isData) {
            String text1 = "Всего забронированно комнат - " + controller.count_room;
            String text2 = "Предпочтительный этаж - " + controller.floor;
            String text3 = "Дата последнего сотрудничества - " + controller.lastDate.toString();

            JLabel textTop = new JLabel(text1);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 4;
            gbc.insets = new Insets(0, 0, 20, 0);
            panel.add(textTop, gbc);

            JLabel textTop2 = new JLabel(text2);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridheight = 1;
            gbc.gridwidth = 4;
            gbc.insets = new Insets(0, 0, 20, 0);
            panel.add(textTop2, gbc);

            JLabel textTop3 = new JLabel(text3);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridheight = 1;
            gbc.gridwidth = 4;
            gbc.insets = new Insets(0, 0, 20, 0);
            panel.add(textTop3, gbc);
        } else{
            JLabel textTop = new JLabel("Нет данных по вашим параметрам.");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 4;
            gbc.insets = new Insets(0, 0, 20, 0);
            panel.add(textTop, gbc);
        }

        add(panel);
        setVisible(true);
    }
}
