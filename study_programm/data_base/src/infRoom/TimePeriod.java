package infRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TimePeriod extends JFrame {
    Controller controller;
    private final JLabel start = new JLabel("");
    private final JLabel finish = new JLabel("");
    String corpusName;
    String roomNumber;

    public TimePeriod(Controller controller_, String corpusName_, String roomNumber_){
        controller = controller_;
        corpusName = corpusName_;
        roomNumber = roomNumber_;

        setSize(300, 220);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Введите период в формате [yyyy]-[mm]-[dd]");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(textTop, gbc);

        JTextField jTextField1 = new JTextField(5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 20, 20, 0);
        panel.add(jTextField1, gbc);

        JTextField jTextField2 = new JTextField(5);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(jTextField2, gbc);

        //запомнить выбор для jTextField
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                start.setText(text);
            }
        };
        jTextField1.addActionListener(actionListenerComboBox);

        ActionListener actionListenerComboBox2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                finish.setText(text);
            }
        };
        jTextField2.addActionListener(actionListenerComboBox2);

        JButton findButton = new JButton("Найти");
        addActionListenerFindButton(findButton);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(findButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerFindButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //проверка корректности
                if(correct())
                    //новое окно
                    new ListGuests(controller, corpusName, roomNumber,start.getText(),finish.getText());
                else new HelpNote("period");
            }
        });
    }

    private boolean correct(){
        try {
            if (start.getText().equals("")) return false;
            if (finish.getText().equals("")) return false;
            Date st = Date.valueOf(start.getText());
            Date fn = Date.valueOf(finish.getText());
            if(st.compareTo(fn) > 0) return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
