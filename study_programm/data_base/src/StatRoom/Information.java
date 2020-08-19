package StatRoom;

import org.omg.CORBA.WStringSeqHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Information extends JFrame {
    Controller controller;

    public Information(Controller controller_, String start, String finish) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Integer sold = controller.getSoldRooms(start, finish);
        Integer all = controller.getAllRooms();

        JLabel textTop = new JLabel("Всего проданно номеров за указанный период: " + sold);
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        JLabel textTop2 = new JLabel("Всего номеров в отеле: " + all);
        textTop2.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop2, gbc);

        Double tmp = getPercent(sold, all);

        JLabel textTop3 = new JLabel("Соотношение в % (sold/all): " + tmp);
        textTop3.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop3, gbc);

        add(panel);
        setVisible(true);
    }

    private static Double getPercent(Integer sold, Integer all){
        double res = ((double)sold * 100.0)/(double)all;
        return res;
    }

}