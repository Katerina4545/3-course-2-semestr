package infCorpus;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCorpus extends Frame {
    Controller controller;
    Integer Id;
    public EditCorpus(Controller controller_, int Id_){
        controller = controller_;
        Id = Id_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //название
        JLabel textTop = new JLabel("Внесите изменения, после этого нажмите enter и \"Сохранить изменения\".");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(textTop, gbc);

        JLabel textTop2 = new JLabel("Для того, чтобы увидеть новые данные, откройте информацию о корпусе снова.");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(textTop2, gbc);

        JLabel textTop3 = new JLabel("Вводите только цифры.");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop3, gbc);

        //таблица
        String[] columnNames = {"касс отеля", "количество этажей", "количество комнат на одном этаже"};
        String[] header = {"1", "2"};
        String[][] data = new String[3][2];
        int i=0;
        while(i<3){
            data[i][0] = columnNames[i];
            data[i][1] = " ";
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
        gbc.gridy = 3;
        gbc.gridheight = 5;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        //кнопка Сохранить изменения
        JButton saveButton = new JButton("Сохранить изменения");
        addActionListenerSaveButton(saveButton, table);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(saveButton, gbc);


        add(panel);
        setVisible(true);
    }

    private void addActionListenerSaveButton(JButton button, JTable table){
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //собрать данные с таблицы
                String classHotel, countFloor, roomFloor;
                classHotel = String.valueOf(table.getValueAt(0, 1));
                countFloor = String.valueOf(table.getValueAt(1, 1));
                roomFloor = String.valueOf(table.getValueAt(2, 1));
                //отправить запрос
                controller.saveChangesCorpus(new String[]{classHotel, roomFloor, countFloor}, Id);
                //просьба просмотреть информацию о корпусе заново
                //закрыть наше окно
                dispose();
            }
        });
    }
}
