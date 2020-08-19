package Guest;

import Guest.Add.Add;
import Guest.Controller;
import Guest.Find.Params;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StartMenu extends JFrame {
    Connection conn;
    Controller controller;

    public StartMenu(Connection conn_) {
        conn = conn_;
        controller = new Controller(conn);

        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();


        JButton findButton = new JButton("Найти постояльца");
        addActionListenerFindButton(findButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 10);
        panel.add(findButton, gbc);

        JButton addButton = new JButton("Добавить постояльца");
        addActionListenerAddButton(addButton);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(addButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerFindButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Params(controller);
            }
        });
    }

    private void addActionListenerAddButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Add(controller);
            }
        });
    }

}
