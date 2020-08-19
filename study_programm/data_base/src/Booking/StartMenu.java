package Booking;

import infCorpus.ShowWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class StartMenu extends JFrame {
    Controller controller;
    public StartMenu(Connection conn){
        controller=new Controller(conn);
        setSize(650, 450);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //список броней
        JButton button1 = new JButton("Список всех броней");
        button1.setPreferredSize(new Dimension(500,100));
        actionListenerAllList(button1);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(button1, gbc);

        //добавить новую
        JButton button2 = new JButton("Добавить новую бронь");
        button2.setPreferredSize(new Dimension(500,100));
        actionListenerAdd(button2);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(button2, gbc);

        //удалить
        JButton button3 = new JButton("Удалить бронь");
        button3.setPreferredSize(new Dimension(500,100));
        actionListenerDelete(button3);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(button3, gbc);

        add(panel);
        setVisible(true);
    }

    private void actionListenerAllList(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BookingList(controller);
            }
        });
    }

    private void actionListenerAdd(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddNew(controller);
            }
        });
    }

    private void actionListenerDelete(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Delete(controller);
            }
        });
    }
}
