package Organization;

import Organization.Add.AddNew;
import Organization.AllStat.Period;
import Organization.Stat.EnterData;

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
        JButton button1 = new JButton("просмотреть общую статистику");
        button1.setPreferredSize(new Dimension(500,100));
        actionListenerAllStat(button1);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(button1, gbc);

        //добавить новую
        JButton button2 = new JButton("Просмотреть статистику по отдельной фирме");
        button2.setPreferredSize(new Dimension(500,100));
        actionListenerStat(button2);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(button2, gbc);

        //удалить
        JButton button3 = new JButton("Добавить новую организацию");
        button3.setPreferredSize(new Dimension(500,100));
        actionListenerAdd(button3);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(button3, gbc);

        add(panel);
        setVisible(true);
    }

    private void actionListenerAllStat(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Period(controller);
            }
        });
    }

    private void actionListenerStat(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EnterData(controller);
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
}

