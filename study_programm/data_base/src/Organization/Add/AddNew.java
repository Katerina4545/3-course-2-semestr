package Organization.Add;

import Organization.Controller;
import Organization.HelpNote;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class AddNew extends JFrame{
    Controller controller;

    public AddNew(Controller controller_) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //таблица
        String[] columnNames = {"НАЗВАНИЕ", "НАЧ_СОТР", "КОН_СОТР"};
        String[] header = {"1", "2"};
        String[][] data = new String[3][2];
        int i = 0;
        while (i < 3) {
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
        column0.setPreferredWidth(110);
        TableColumn column1 = table.getColumnModel().getColumn(1);
        column1.setPreferredWidth(110);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        //кнопка сохранить
        JButton saveButton = new JButton("Добавить новую организацию");
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
                String[] data = new String[3];
                for (int i = 0; i < 3; i++) {
                    data[i] = (String) table.getValueAt(i, 1);
                }
                if (correctData(data)) {
                    controller.addOrg(data);
                } else new HelpNote("add");
                dispose();
            }
        });
    }

    //"НАЗВАНИЕ", "НАЧ_СОТР", "КОН_СОТР"
    private boolean correctData(String[] data) {
        try {
            char ch = data[0].charAt(0);
            if(ch != '\'') return false;
            Date start = Date.valueOf(data[1]);
            Date finish = Date.valueOf(data[2]);
            if(start.compareTo(finish) > 0) return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
