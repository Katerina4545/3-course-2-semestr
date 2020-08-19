package Roles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import Booking.StartMenu;

public class Booking extends JFrame{
    Connection conn;
    public Booking(Connection conn_){
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

        JButton b1 = new JButton("Бронь номеров");
        b1.setPreferredSize(new Dimension(500,50));
        booking(b1);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b1, gbc);

        JButton b2 = new JButton("Постоялец");
        b2.setPreferredSize(new Dimension(500,50));
        guest(b2);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b2, gbc);

        JButton b3 = new JButton("Организации");
        b3.setPreferredSize(new Dimension(500,50));
        org(b3);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b3, gbc);

        JButton b4 = new JButton("Популярность номеров");
        b4.setPreferredSize(new Dimension(500,50));
        popular(b4);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b4, gbc);

        JButton b5 = new JButton("Занятость номеров");
        b5.setPreferredSize(new Dimension(500,50));
        aboutRoom(b5);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(b5, gbc);

        JButton b6 = new JButton("История заселений");
        b6.setPreferredSize(new Dimension(500,50));
        history(b6);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(b6, gbc);

        add(panel);
        setVisible(true);
    }

    private void booking(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new StartMenu(conn);
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

    private void org(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Organization.StartMenu(conn);
            }
        });
    }

    private void popular(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PopularRooms.StartMenu(conn);
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

    private void history(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HistoryGuest.StartMenu(conn);
            }
        });
    }
}
