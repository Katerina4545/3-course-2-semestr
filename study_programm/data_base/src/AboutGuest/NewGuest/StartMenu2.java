package AboutGuest.NewGuest;
import AboutGuest.Controller;
import AboutGuest.HelpNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu2 extends JFrame{
    Controller controller;
    private final JLabel select = new JLabel(" ");

    public StartMenu2(Controller controller_) {
        controller = controller_;
        setSize(220, 220);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Выберите корпус");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        String[] corpusNames = controller.getCorpusNames();
        JComboBox jComboBox = new JComboBox(corpusNames);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 40, 0);
        panel.add(jComboBox, gbc);

        JButton selectButton = new JButton("Выбрать");
        addActionListenerSelectButton(selectButton);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(selectButton, gbc);

        //запомнить выбор для jcombobox
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                String text = (String) box.getSelectedItem();
                select.setText(text);
            }
        };
        jComboBox.addActionListener(actionListenerComboBox);

        add(panel);
        setVisible(true);

    }

    private void addActionListenerSelectButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (select.getText() == " " || select.getText() == null) new HelpNote("jComboBox");
                //новое окно
                else new NewGuest(controller, select.getText());
                dispose();
            }
        });
    }
}
