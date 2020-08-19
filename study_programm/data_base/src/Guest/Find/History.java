package Guest.Find;

        import Guest.Controller;

        import javax.swing.*;
        import javax.swing.table.TableColumn;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class History extends JFrame {
    Controller controller;

    public History(Controller controller_, Integer idGuest) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //заголовок
        JLabel textTop = new JLabel("История заселений");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(textTop, gbc);

        //таблица
        String[] header = {"корпус", "номер", "заселение", "выезд", "счет"};
        String[][] data = controller.getAllHistory(idGuest);
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

    private void addActionListenerSaveButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
    }

}