package Organization.AllStat;

import Organization.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class List extends JFrame{
    Controller controller;

    public List(Controller controller_) {
        controller = controller_;
        setSize(700, 400);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Список организаций");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(textTop, gbc);

        JTextArea jTextArea = new JTextArea(8, 10);
        String[] orgList = controller.getOrgList();
        for(int i=0; i<orgList.length;i++){
            jTextArea.setText(orgList[i]);
        }
        jTextArea.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(new JScrollPane(jTextArea), gbc);


        add(panel);
        setVisible(true);
    }

    private void addActionListenerSaveButton(JButton button, JTable table) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });
    }

}
