package Roles;

import AddСomplaint.enterData;
import CurrentComplaint.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Admin extends JFrame{
    Connection conn;
    public Admin(Connection conn_){
        conn = conn_;
        setSize(700, 600);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Выберите форму.");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        JButton b1 = new JButton("Текущие жалобы");
        b1.setPreferredSize(new Dimension(500,50));
        currentComplaint(b1);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b1, gbc);

        JButton b2 = new JButton("Добавить жалобу");
        b2.setPreferredSize(new Dimension(500,50));
        addComplaint(b2);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b2, gbc);

        JButton b3 = new JButton("Свободные/занятые номера");
        b3.setPreferredSize(new Dimension(500,50));
        aboutRoom(b3);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b3, gbc);

        JButton b4 = new JButton("Постоялец");
        b4.setPreferredSize(new Dimension(500,50));
        guest(b4);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b4, gbc);

        JButton b5 = new JButton("История заселений");
        b5.setPreferredSize(new Dimension(500,50));
        historyGuest(b5);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b5, gbc);

        JButton b6 = new JButton("Частые гости и новые клиенты");
        b6.setPreferredSize(new Dimension(500,50));
        aboutGuest(b6);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b6, gbc);

        JButton b7 = new JButton("Информация о номерах");
        b7.setPreferredSize(new Dimension(500,50));
        infRoom(b7);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(b7, gbc);

        add(panel);
        setVisible(true);
    }

    private void currentComplaint(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StartMenu(conn);
            }
        });
    }

    private void addComplaint(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new enterData(conn);
            }
        });
    }

    private void aboutRoom(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AboutRoom.StartMenu(conn);
            }
        });
    }

    private void guest(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Guest.StartMenu(conn);
            }
        });
    }

    private void historyGuest(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HistoryGuest.StartMenu(conn);
            }
        });
    }

    private void aboutGuest(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AboutGuest.StartMenu(conn);
            }
        });
    }

    private void infRoom(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new infRoom.StartMenu(conn);
            }
        });
    }
}
