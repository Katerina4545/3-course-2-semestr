package AboutGuest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class StartMenu extends JFrame {
    Controller controller;
    private final JLabel start = new JLabel(" ");
    private final JLabel finish = new JLabel(" ");

    public StartMenu(Connection conn_) {
        controller = new Controller(conn_);
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Выберите функцию");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        JButton selectButton = new JButton("Просмотреть частых клиентов");
        addActionListenerSelectButton(selectButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(selectButton, gbc);

        JButton newButton = new JButton("Просмотреть новых клиентов");
        addActionListenerNewButton(newButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(newButton, gbc);


        add(panel);
        setVisible(true);
    }

    private void addActionListenerSelectButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AboutGuest.FrequentGuest.StartMenu2(controller);
            }
        });
    }

    private void addActionListenerNewButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AboutGuest.NewGuest.StartMenu2(controller);
            }
        });
    }
}

