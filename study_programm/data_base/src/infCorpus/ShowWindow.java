package infCorpus;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ShowWindow  extends JFrame {
    Connection conn;
    Controller controller;
    public ShowWindow(Connection conn_, String[] corpusInf, Controller controller_){
        conn = conn_;
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //название
        JLabel textTop = new JLabel(corpusInf[1]);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        //таблица
        String[] columnNames = {"номер", "название", "касс отеля", "количество этажей", "количество комнат на одном этаже"};
        String[] header = {"1", "2"};
        String[][] data = new String[5][2];
        int i=0;
        while(i<5){
            data[i][0] = columnNames[i];
            data[i][1] = corpusInf[i];
            i++;
        }
        JTable table = new JTable(data, header){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column1 =table.getColumnModel().getColumn(0);
        column1.setPreferredWidth(250);
        TableColumn column2 =table.getColumnModel().getColumn(1);
        column2.setPreferredWidth(250);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 5;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        //кнопка дополнительные усл
        JButton serviceButton = new JButton("Дополнительные услуги");
        addActionListenerServiceButton(serviceButton, Integer.valueOf(data[0][1]));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 10);
        panel.add(serviceButton, gbc);

        //кнпка изменить
        JButton editButton = new JButton("Изменить");
        addActionListenerEditButton(editButton, Integer.valueOf(data[0][1]));
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(editButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerServiceButton(JButton button, int Id) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //новое окно
                new Service(controller, Id);
            }
        });
    }

    private void addActionListenerEditButton(JButton button, int Id) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //новое окно
                new EditCorpus(controller, Id);
            }
        });
    }
}
