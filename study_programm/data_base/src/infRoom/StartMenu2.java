package infRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu2 extends JFrame{
    private Controller controller;
    private final JLabel select = new JLabel(" ");
    private String corpusName;

    public StartMenu2(String corpusName_, Controller controller_){
        controller = controller_;
        corpusName = corpusName_;

        setSize(300, 220);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Введите номер");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(textTop, gbc);

        String[] numbers = controller.getRoomNumbers(corpusName);
        JComboBox jComboBox = new JComboBox(numbers);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(jComboBox, gbc);

        //запомнить выбор для jTextField
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                String text = (String) box.getSelectedItem();
                select.setText(text);
            }
        };
        jComboBox.addActionListener(actionListenerComboBox);


        JButton createButton = new JButton("Создать");
        addActionListenerCreateButton(createButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 10);
        panel.add(createButton, gbc);

        JButton selectButton = new JButton("Выбрать");
        addActionListenerSelectButton(selectButton);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(selectButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerCreateButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //новое окно
                new CreateRoom(corpusName, controller);
            }
        });
    }

    private void addActionListenerSelectButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (select.getText() == " " || select.getText() == null) return;
                //новое окно
                new ShowWindow(select.getText(), corpusName, controller);
            }
        });
    }
}
