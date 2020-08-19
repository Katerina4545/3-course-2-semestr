package AddСomplaint;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;

public class enterData extends JFrame {
    Controller controller;

    public enterData(Connection conn) {
        controller=new Controller(conn);
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //таблица
        String[] columnNames = {"ФИО", "организация", "корпус", "номер", "описание"};
        String[] header = {"1", "2"};
        String[][] data = new String[5][2];
        int i = 0;
        while (i < 5) {
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
        JButton saveButton = new JButton("Добавить жалобу");
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
                String[] data = new String[5];
                for (int i = 0; i < 5; i++) {
                    data[i] = (String) table.getValueAt(i, 1);
                }
                if (correctData(data)) {
                    controller.addComplaint(data);
                    dispose();
                } else new HelpNote(controller,"add");
            }
        });
    }

    //String[] columnNames = {"ФИО", "организация", "корпус", "номер", "описание"};
    private boolean correctData(String[] data) {
        try {
            if (!controller.organizationExist(data[1])) return false;
            if (!controller.corpusExist(data[2])) return false;
            if (!controller.roomExist(Integer.valueOf(data[3]), data[2])) return false;
            if(data[4].equals("")) return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}