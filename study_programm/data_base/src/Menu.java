import Guest.StartMenu;
import Roles.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Menu extends JFrame {
    Connection conn;
    private final JLabel login = new JLabel("");
    private final JLabel password = new JLabel("");
    public Menu(Connection conn_) {
        conn = conn_;
        setSize(700, 600);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Введите логин");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        JTextField jTextField1 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField1, gbc);

        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                login.setText(text);
            }
        };
        jTextField1.addActionListener(actionListenerComboBox);


        JLabel textTop2 = new JLabel("Введите пароль");
        textTop2.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop2, gbc);

        JTextField jTextField2 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField2, gbc);

        ActionListener actionListenerComboBox2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                password.setText(text);
            }
        };
        jTextField2.addActionListener(actionListenerComboBox2);

        JButton nextButton = new JButton("Далее");
        addActionListenerNextButton(nextButton);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(nextButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerNextButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //найти пользователя, либо выдать что-то такого нет
                try {
                    Statement statement = conn.createStatement();
                    String sql = "select POSITION from USERS where"
                            + " LOGIN='" + login.getText()
                            + "' and PASSWORD="+password.getText();
                    System.out.println(sql);
                    ResultSet resultSet = statement.executeQuery(sql);
                    resultSet.next();
                    int pos = resultSet.getInt("POSITION");
                    switch(pos){
                        case 1:
                            new AdminCorpus(conn);
                            break;
                        case 2:
                            new Admin(conn);
                            break;
                        case 3:
                            new Booking(conn);
                            break;
                        case 4:
                            new Guest.StartMenu(conn);
                            break;
                    }
                    dispose();
                }catch (Exception ex){
                    ex.printStackTrace();
                    new Roles.Help();
                }
            }
        });
    }
}