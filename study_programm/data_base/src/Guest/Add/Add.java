package Guest.Add;

import Guest.HelpNote;
import Guest.Controller;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class Add extends JFrame {
    Controller controller;

    public Add(Controller controller_) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //таблица
        String[] columnNames = {"назв.организации(как в бд)", "дата заселения", "дата выезда", "фио", "долг за доп.ус.",
                "назв. корпуса(как в бд)","номер"};
        String[] header = {"1", "2"};
        String[][] data = new String[7][2];
        int i = 0;
        while (i < 7) {
            data[i][0] = columnNames[i];
            data[i][1] = "";
            i++;
        }
        JTable table = new JTable(data, header) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 1) return true;
                else return false;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column0 = table.getColumnModel().getColumn(0);
        column0.setPreferredWidth(170);
        TableColumn column1 = table.getColumnModel().getColumn(1);
        column1.setPreferredWidth(110);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        //кнопка сохранить
        JButton saveButton = new JButton("Добавить нового постояльца");
        addActionListenerSaveButton(saveButton, table);
        gbc.gridx = 0;
        gbc.gridy = 5;
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
                String[] data = new String[7];
                for (int i = 0; i < 7; i++) {
                    data[i] = (String) table.getValueAt(i, 1);
                }
                if (correctData(data)) {
                    controller.addGuest(data);
                } else new HelpNote(controller,"add");
                dispose();
            }
        });
    }

    //columnNames = {"организация", "дата заселения", "дата выезда", "фио", "долг за доп.ус.", "корпус",
    //                "номер"};
    private boolean correctData(String[] data) {
        try {
            //корректные даты
            Date start = Date.valueOf(data[1]);
            Date finish = Date.valueOf(data[2]);
            //номер не занят
            Date now = new Date(System.currentTimeMillis());
            if(now.compareTo(start)>=0 && now.compareTo(finish)<=0) {
                if(controller.numberIsBusy(data[5], data[6]))return false;
            }
            if (!controller.organizationExist(data[0])) return false;
            if (!controller.corpusExist(data[5])) return false;
            if (!controller.roomExist(Integer.valueOf(data[6]), data[5])) return false;
            //проверка на долг
            Double.valueOf(data[4]);

            if(start.compareTo(finish) > 0) return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
