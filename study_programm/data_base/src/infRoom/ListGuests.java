package infRoom;

import javax.swing.*;
import java.awt.*;

public class ListGuests extends JFrame {
    Controller controller;
    public ListGuests(Controller controller_, String corpusName, String roomNumber, String start, String finish){
        controller = controller_;

        setSize(300, 220);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel textTop = new JLabel("Список постояльцев");
        textTop.setVerticalAlignment(JLabel.TOP);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(textTop, gbc);

        JTextArea jTextArea = new JTextArea(8, 10);
        String[] data = controller.getPeriodDate(corpusName, roomNumber, start, finish);
        int i=0;
        while (i<data.length){
            if(data[i]!=null)
                jTextArea.setText(data[i]);
            i++;
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
}
