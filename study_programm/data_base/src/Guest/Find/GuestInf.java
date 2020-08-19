package Guest.Find;

import Guest.Controller;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestInf extends JFrame {
    Controller controller;
    String[] lastDataGuest;

    public GuestInf(Controller controller_, String orgName, String name) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel(name);
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        //0ID_ЗАПИСЬ	1ID_ПОСТОЯЛЕЦ	2ID_КОРПУС	3ID_НОМЕР	4ДАТА_ЗАСЕЛ	5ДАТА_ВЫЕЗД	6ДОЛГ
        lastDataGuest = controller.getLastInf(orgName, name);
        //таблица
        String[] columnNames = {"организация", "дата засел", "дата выезда", "долг за доп усл"};
        String[] header = {"1", "2"};
        String[][] resultData = new String[4][2];
        int i = 0;
        while (i < 4) {
            resultData[i][0] = columnNames[i];
            i++;
        }
        resultData[0][1] = orgName;
        resultData[1][1] = lastDataGuest[4];
        resultData[2][1] = lastDataGuest[5];
        resultData[3][1] = lastDataGuest[6];
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
        gbc.gridy = 2;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(table, gbc);

        JButton addCredit = new JButton("Добавить долг");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(addCredit, gbc);
        AddServiceButton(addCredit);

        JButton addComplaint = new JButton("Жалобы");
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(addComplaint, gbc);
        addComplaintButton(addComplaint);

        JButton settle = new JButton("Поселить");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(settle, gbc);
        settleButton(settle);

        JButton historyService = new JButton("История доп.услуг");
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(historyService, gbc);
        historyServiceButton(historyService);

        JButton historyWork = new JButton("История сотрудничества");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(historyWork, gbc);
        historyWorkButton(historyWork);

        JButton evict = new JButton("Освободить номер");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(evict, gbc);
        evictButton(evict);

        add(panel);
        setVisible(true);
    }

    private void AddServiceButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddService(controller, lastDataGuest);
            }
        });
    }

    private void addComplaintButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ShowComplaint(controller, Integer.valueOf(lastDataGuest[1]));
            }
        });
    }

    private void settleButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Settle(controller, Integer.valueOf(lastDataGuest[1]));
            }
        });
    }


    private void historyServiceButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new ShowService(controller, lastDataGuest);
            }
        });
    }

    private void historyWorkButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new History(controller, Integer.valueOf(lastDataGuest[1]));
            }
        });
    }

    private void evictButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Evict(controller, lastDataGuest);
            }
        });
    }

}
