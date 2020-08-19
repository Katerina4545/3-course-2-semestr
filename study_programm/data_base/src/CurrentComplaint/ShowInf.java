
package CurrentComplaint;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowInf extends JFrame {
    Controller controller;

    public ShowInf(Controller controller_, String complaint) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] data = controller.getInf(complaint);
        //таблица
        String[] columnNames = {"постоялец", "корпус", "статус", "описание"};
        String[] header = {"1", "2"};
        String[][] resultData = new String[4][2];
        int i = 0;
        while (i < 4) {
            resultData[i][0] = columnNames[i];
            i++;
        }
        resultData[0][1] = data[0];
        resultData[1][1] = data[1];
        resultData[2][1] = data[2];
        resultData[3][1] = data[3];
        JTable table = new JTable(resultData, header) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn column1 = table.getColumnModel().getColumn(0);
        column1.setPreferredWidth(110);
        TableColumn column2 = table.getColumnModel().getColumn(1);
        column2.setPreferredWidth(110);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        JButton selectButton = new JButton("Изменить статус");
        addActionListenerSelectButton(selectButton, complaint);
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(selectButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerSelectButton(JButton button, String complaint) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Change(controller, complaint);
            }
        });
    }

}