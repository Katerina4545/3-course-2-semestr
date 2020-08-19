package Organization.Stat;

import Organization.Controller;
import Organization.HelpNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class EnterData extends JFrame{
    Controller controller;
    private final JLabel select = new JLabel("");
    private final JLabel start = new JLabel("");
    private final JLabel finish = new JLabel("");

    public EnterData(Controller controller_) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Выберите организацию и введите период бронирования.");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        panel.add(textTop, gbc);

        String[] corpusNames = controller.getAllNamesOrg();
        JComboBox jComboBox = new JComboBox(corpusNames);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jComboBox, gbc);

        //запомнить выбор для jcombobox
        ActionListener actionListenerComboBox = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                String text = (String) box.getSelectedItem();
                select.setText(text);
            }
        };
        jComboBox.addActionListener(actionListenerComboBox);

        JTextField jTextField1 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField1, gbc);

        JTextField jTextField2 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(jTextField2, gbc);

        //запомнить выбор для jTextField
        ActionListener actionListenerComboBox2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                start.setText(text);
            }
        };
        jTextField1.addActionListener(actionListenerComboBox2);

        ActionListener actionListenerComboBox3 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                finish.setText(text);
            }
        };
        jTextField2.addActionListener(actionListenerComboBox3);

        JButton nextButton = new JButton("Далее");
        addActionListenerSaveButton(nextButton);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(nextButton, gbc);

        add(panel);
        setVisible(true);
    }

    private void addActionListenerSaveButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(correctInput()) {
                    controller.getOneStat(start.getText(), finish.getText(), select.getText());
                    new ViewResult(controller);
                }else new HelpNote("stat");
                dispose();
            }
        });
    }

    private boolean correctInput(){
        try {
            Date st = Date.valueOf(start.getText());
            Date fin = Date.valueOf(finish.getText());
            if (st.compareTo(fin) > 0) return false;
            if(!controller.orgExist(select.getText())) return false;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
