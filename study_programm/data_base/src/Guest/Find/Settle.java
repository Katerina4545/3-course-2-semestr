package Guest.Find;
import Guest.Controller;
import Guest.HelpNote;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class Settle extends JFrame {
    Controller controller;

    public Settle(Controller controller_, Integer idGuest) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //ID_ЗАПИСЬ	ID_ПОСТОЯЛЕЦ	ID_КОРПУС	ID_НОМЕР	ДАТА_ЗАСЕЛ	ДАТА_ВЫЕЗД	ДОЛГ
        //таблица
        String[] columnNames = {"корпус", "номер", "заселение", "выезд", "долг"};
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
        JButton saveButton = new JButton("Сохранить");
        addActionListenerSaveButton(saveButton, table, idGuest);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(saveButton, gbc);


        add(panel);
        setVisible(true);
    }

    private void addActionListenerSaveButton(JButton button, JTable table, Integer idGuest) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] data = new String[5];
                for (int i = 0; i < 5; i++) {
                    data[i] = (String) table.getValueAt(i, 1);
                }
                if (correctData(data)) {
                    controller.addHistoryNote(data, idGuest);
                }else {
                    new HelpNote(controller, "settle");
                }
                dispose();
            }
        });
    }

    //String[] columnNames = {0"корпус", 1"номер", 2"заселение", 3"выезд", 4"долг"};
    private boolean correctData(String[] data) {
        try {
            if (!controller.corpusExist(data[0])) return false;
            Date start = Date.valueOf(data[2]);
            Date finish = Date.valueOf(data[3]);
            Date now = new Date(System.currentTimeMillis());
            if (now.compareTo(start) >= 0 && now.compareTo(finish) <= 0) {
                if (controller.numberIsBusy(data[0], data[1])) return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}