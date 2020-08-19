package Booking;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class BookingList extends JFrame {
    Controller controller;
    public BookingList(Controller controller_){
        controller=controller_;
        setSize(900, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //заголовок
        JLabel textTop = new JLabel("Сущесвующие брони");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(textTop, gbc);

        //таблица
        String[] header = {"организация", "кол_во человек", "кол_во комнат", "желаемый этаж", "заселение", "выезд",
                "скидка", "стоимость"};
        String[][] data = controller.getAllBooking();
        JTable table = new JTable(data, header){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        //ширина столбцов
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column1 =table.getColumnModel().getColumn(1);
        column1.setPreferredWidth(110);
        TableColumn column2 =table.getColumnModel().getColumn(2);
        column2.setPreferredWidth(110);
        TableColumn column3 =table.getColumnModel().getColumn(3);
        column3.setPreferredWidth(110);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(table, gbc);

        add(panel);
        setVisible(true);
    }
}
