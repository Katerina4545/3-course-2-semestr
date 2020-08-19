package infCorpus;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CreateWindow extends JFrame{
    private Connection conn;
    private Controller controller;
    public CreateWindow(Connection conn_, Controller controller_){
        conn = conn_;
        controller = controller_;

        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //заголовок
        JLabel textTop = new JLabel("Введите данные.");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(textTop, gbc);

        //таблица
        String[] columnNames = {"номер", "название", "класс отеля", "количество этажей", "количество комнат на одном этаже"};
        String[] header = {"1", "2"};
        String[][] data = new String[5][2];
        int i=0;
        while(i<5){
            data[i][0] = columnNames[i];
            data[i][1] = "";
            i++;
        }
        JTable table = new JTable(data, header){
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 1) return true;
                else return false;
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

        //кнопка сохранить изменения
        JButton saveButton = new JButton("Сохранить изменения");
        addActionListenerSaveButton(saveButton, table);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(saveButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerSaveButton(JButton button, JTable table) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(correctData(table)) {
                    String[] data = new String[5];
                    for(int i=0; i<5; i++){
                        data[i] = (String)table.getValueAt(i,1);
                    }
                    controller.createCorpus(data);
                }
                else new HelpNote(controller.getMaxNumberCorpus());
                dispose();
            }
        });
    }

    private boolean correctData(JTable table) {
        String number = (String)table.getValueAt(0,1);
        String class_hotel = (String)table.getValueAt(2,1);
        String count_floor = (String)table.getValueAt(3,1);
        String count_room = (String)table.getValueAt(4,1);

        if(!isNumber(number) || !isNumber(class_hotel) || !isNumber(count_floor) || !isNumber(count_room)) return false;

        int max_number = controller.getMaxNumberCorpus();
        if(Integer.valueOf(number) >= max_number) return false;
        String name = (String)table.getValueAt(1,1);
        if(name.length() >= 50) return false;
        if(Integer.valueOf(class_hotel) < 1 || Integer.valueOf(class_hotel) > 5) return false;
        if(Integer.valueOf(count_floor) < 1) return false;
        if(Integer.valueOf(count_room) < 1) return false;

        return true;
    }

    private boolean isNumber(String ch) {
        if (!ch.trim().equals("")) {
            int b = Integer.parseInt(ch.trim());
            return true;
        } else
            return false;
    }
}
