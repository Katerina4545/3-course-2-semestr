package infRoom;


import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateRoom extends JFrame {
    String corpusName;
    Controller controller;
    public CreateRoom(String corpusName_, Controller controller_){
        corpusName = corpusName_;
        controller = controller_;

        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //заголовок
        JLabel textTop = new JLabel("Введите данные.");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(textTop, gbc);

        //таблица
        String[] columnNames = {"номер", "занят", "оценка", "местность", "цена в сутки"};
        String[] header = {"1", "2"};
        String[][] data = new String[5][2];
        int i=0;
        while(i<5){
            data[i][0] = columnNames[i];
            data[i][1] = "";
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
        gbc.gridy = 1;
        gbc.gridheight = 5;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        //кнопка сохранить изменения
        JButton saveButton = new JButton("Сохранить изменения");
        addActionListenerSaveButton(saveButton, table);
        gbc.gridx = 1;
        gbc.gridy = 6;
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
                if(correctData(table)) {
                    String[] data = new String[5];
                    for(int i=0; i<5; i++){
                        data[i] = (String)table.getValueAt(i,1);
                    }
                    controller.createRoom(data, corpusName);
                    dispose();
                }
                else new HelpNote("create");
            }
        });
    }

    private boolean correctData(JTable table){
        try {
            String tmp = (String) table.getValueAt(0, 1);
            Integer number = Integer.valueOf(tmp);
            if (number / 1000 != 0) return false;
            if (number / 100 > controller.maxFloor(corpusName)) return false;
            if (number % 100 > controller.maxRoomFloor(corpusName)) return false;

            String employed = (String) table.getValueAt(1, 1);
            if (!employed.equals("yes") && !employed.equals("no")) return false;

            String tmp2 = (String) table.getValueAt(2, 1);
            Double popularity = Double.valueOf(tmp2);
            if (popularity > 100) return false;

            String tmp3 = (String) table.getValueAt(3, 1);
            Integer capacity = Integer.valueOf(tmp3);
            if(capacity > 5) return false;

            String tmp4 = (String) table.getValueAt(4,1);
            Double price = Double.valueOf(tmp4);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
