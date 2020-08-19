package Booking;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class AddNew extends JFrame {
    Controller controller;

    public AddNew(Controller controller_) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //таблица
        String[] columnNames = {"организация", "кол_во человек", "кол_во комнат", "желаемый этаж", "заселение", "выезд",
                "скидка", "стоимость"};
        String[] header = {"1", "2"};
        String[][] data = new String[8][2];
        int i = 0;
        while (i < 8) {
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
        JButton saveButton = new JButton("Добавить новую бронь");
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
                String[] data = new String[8];
                for (int i = 0; i < 8; i++) {
                    data[i] = (String) table.getValueAt(i, 1);
                }
                if (correctData(data)) {
                    controller.addBooking(data);
                } else new HelpNote(controller,"add");
                dispose();
            }
        });
    }

    //{"организация", "кол_во человек", "кол_во комнат", "желаемый этаж", "заселение", "выезд",
    //                "скидка", "стоимость"};
    private boolean correctData(String[] data) {
        try {
            if (!controller.organizationExist(data[0])) return false;
            Integer.valueOf(data[1]);
            Integer.valueOf(data[2]);
            Integer.valueOf(data[3]);
            Date start = Date.valueOf(data[4]);
            Date finish = Date.valueOf(data[5]);
            if(start.compareTo(finish) > 0) return false;
            Double.valueOf(data[6]);
            Double.valueOf(data[7]);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
