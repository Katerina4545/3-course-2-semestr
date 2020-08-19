package infRoom;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowWindow extends JFrame {
    String roomNumber;
    String corpusName;
    Controller controller;

    public ShowWindow(String roomNumber_, String corpusName_, Controller controller_){
        roomNumber = roomNumber_;
        corpusName = corpusName_;
        controller = controller_;

        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //запрос на данные
        String[] data = controller.getInfRoom(roomNumber, corpusName);

        //таблица
        String[] columnNames = {"номер", "занят", "оценка", "местность", "цена в сутки"};
        String[] header = {"1", "2"};
        String[][] resultData = new String[5][2];
        int i=0;
        while(i<5){
            resultData[i][0] = columnNames[i];
            resultData[i][1] = data[i];
            i++;
        }
        JTable table = new JTable(resultData, header){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column1 =table.getColumnModel().getColumn(0);
        column1.setPreferredWidth(250);
        TableColumn column2 =table.getColumnModel().getColumn(1);
        column2.setPreferredWidth(250);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 5;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        //кнопка сохранить изменения
        JButton periodButton = new JButton("Занятость в период");
        addActionListenerPeriodButton(periodButton, table);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(periodButton, gbc);

        //кнопка сохранить изменения
        JButton editButton = new JButton("Изменить");
        addActionListenerEditButton(editButton, table);
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(editButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerPeriodButton(JButton button, JTable table) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TimePeriod(controller, corpusName, roomNumber);
            }
        });
    }

    private void addActionListenerEditButton(JButton button, JTable table) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditRoom(controller, corpusName, roomNumber);
            }
        });
    }

}
