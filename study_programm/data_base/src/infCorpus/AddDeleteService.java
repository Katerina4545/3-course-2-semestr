package infCorpus;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;

public class AddDeleteService extends Frame{
    Controller controller;
    Integer IdCorpus;
    public AddDeleteService(Controller controller_, int IdCorpus_, String[][]data) {
        controller = controller_;
        IdCorpus = IdCorpus_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //название
        JLabel textTop = new JLabel("В столбец \"наличие в корпусе\" вводите значения \"yes\" или \"no\"");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(textTop, gbc);

        JLabel textTop3 = new JLabel("После ввода всех данных нажмите enter, а затем охранить изменения.");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(textTop3, gbc);

        JLabel textTop2 = new JLabel("Для отображения новых данных откройте дополнительные услуги заново.");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(textTop2, gbc);

        //таблица
        JTable table = new JTable(data, new String[]{"номер", "описание", "цена", "наличие в корпусе"}) {
            @Override
            public boolean isCellEditable(int row, int col) {
                if (col == 3) return true;
                else return false;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column1 =table.getColumnModel().getColumn(1);
        column1.setPreferredWidth(150);
        TableColumn column3 =table.getColumnModel().getColumn(3);
        column3.setPreferredWidth(150);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        //кнопка
        JButton saveButton = new JButton("Сохранить изменения");
        addActionListenerSaveButton(saveButton, table);
        gbc.gridx = 2;
        gbc.gridy = 7 ;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(saveButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerSaveButton(JButton button, JTable table) {
        HashMap<Integer, String> newData = new HashMap<>();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean not_error = true;
                for(int i=1; i<table.getRowCount(); i++){
                    //System.out.println(table.getValueAt(i,0));
                    Integer number = Integer.valueOf((String)table.getValueAt(i,0));
                    String value = (String)table.getValueAt(i,3);
                    if(!value.equals("yes") && !value.equals("no")) not_error = false;
                    newData.put(number, value);
                }
                if(not_error) controller.saveChangesService(IdCorpus, newData);
                dispose();
                return;
            }
        });
    }
}
