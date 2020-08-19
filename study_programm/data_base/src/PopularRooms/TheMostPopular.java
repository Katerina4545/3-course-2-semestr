package PopularRooms;

import Organization.Stat.EnterData;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TheMostPopular extends JFrame {
    Controller controller;

    public TheMostPopular(Controller controller_, String corpus) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Самые популярные номера");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        String[][] data = controller.getMostPopular(corpus);
        //таблица
        String[] header = {"номер", "оценка"};
        JTable table = new JTable(data, header){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //ширина столбцов
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column1 =table.getColumnModel().getColumn(0);
        column1.setPreferredWidth(110);
        TableColumn column2 =table.getColumnModel().getColumn(1);
        column2.setPreferredWidth(110);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(table, gbc);

        JButton selectButton = new JButton("Другой номер");
        addActionListenerSelectButton(selectButton, corpus);
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(selectButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerSelectButton(JButton button, String corpus) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EnterOtherRoom(controller, corpus);
            }
        });
    }

}