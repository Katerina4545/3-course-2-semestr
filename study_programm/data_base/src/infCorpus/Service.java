package infCorpus;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

public class Service extends Frame {
    Controller controller;
    public Service(Controller controller_, int IdCorpus) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        //название
        JLabel textTop = new JLabel("Дополнительные услуги корпуса");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        //таблица
        String[] header = {"номер", "описание", "цена", "наличие в корпусе"};
        String[][] data = controller.getServiceCorpus(IdCorpus);
        JTable table = new JTable(data, header){
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column1 =table.getColumnModel().getColumn(1);
        column1.setPreferredWidth(150);
        TableColumn column3 =table.getColumnModel().getColumn(3);
        column3.setPreferredWidth(150);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        //кнопка добавить/удалить
        JButton addButton = new JButton("Добавить/Удалить услугу");
        addActionListenerAddDeleteButton(addButton, IdCorpus, data);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(addButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerAddDeleteButton(JButton button, int IdCorpus, String[][] data) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddDeleteService(controller, IdCorpus, data);
            }
        });
    }
}
