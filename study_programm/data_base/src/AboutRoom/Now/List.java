package AboutRoom.Now;


import AboutRoom.Controller;

import javax.swing.*;
import java.awt.*;

public class List extends JFrame {
    Controller controller;

    public List(Controller controller_, String corpusName) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[][] data = controller.getNowList(corpusName);

        //таблица
        String[] header = {"номер", "популярность", "местность", "цена"};
        JTable table = new JTable(data, header){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(table, gbc);

        add(panel);
        setVisible(true);
    }
}
