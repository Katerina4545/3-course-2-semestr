package AboutRoom;

import AboutRoom.Date.EnterDate;
import AboutRoom.Parameters.EnterParameters;
import AboutRoom.Now.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
Получить количество свободных номеров на данный момент.
Получить сведения о количестве свободных номеров с указанными характеристиками.
Получить список занятых сейчас номеров, которые освобождаются к указанному сроку.
*/


public class StartMenu2 extends JFrame {
    private Controller controller;
    private final JLabel select = new JLabel(" ");
    private String corpusName;

    public StartMenu2(Controller controller_, String corpusName_) {
        controller = controller_;
        corpusName = corpusName_;

        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton button1 = new JButton("сведения о свободных номерах на данный момент");
        button1.setPreferredSize(new Dimension(500, 100));
        one(button1);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(button1, gbc);

        JButton button2 = new JButton("сведения о свободных номерах с указанными характеристиками");
        button2.setPreferredSize(new Dimension(500, 100));
        two(button2);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(button2, gbc);

        JButton button3 = new JButton("список занятых сейчас номеров, которые освобождаются к указанному сроку");
        button3.setPreferredSize(new Dimension(500, 100));
        three(button3);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(20, 0, 20, 0);
        panel.add(button3, gbc);

        add(panel);
        setVisible(true);
    }

    private void one(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new List(controller, corpusName);
            }
        });
    }

    private void two(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EnterParameters(controller, corpusName);
            }
        });
    }

    private void three(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EnterDate(controller, corpusName);
            }
        });
    }
}
