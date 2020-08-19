package AboutGuest.FrequentGuest;


import AboutGuest.Controller;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class List extends JFrame {
    Controller controller;

    public List(Controller controller_, String corpus, String start, String finish, Boolean allTime) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String [][] data = controller.getFrequentGuests(corpus, start, finish, allTime);

        //таблица
        String[] header = {"ФИО", "организация"};
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

        add(panel);
        setVisible(true);
    }

    private void addActionListenerSaveButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
    }

}