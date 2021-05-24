package ui;

import javax.swing.*;
import java.awt.*;

public class NotificationWindow {

    private static final JFrame root = new JFrame();
    private static final JTextArea errorArea = new JTextArea();
    private static final JLabel lblNewLabel = new JLabel("Error:");

    public static void run(String message,String flag) {

        root.setBounds(100, 100, 450, 139);
        root.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        root.setResizable(false);
        root.getContentPane().setLayout(null);

        lblNewLabel.setText(flag);
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 28));
        lblNewLabel.setBounds(10, 11, 69, 33);
        root.getContentPane().add(lblNewLabel);

        errorArea.setBounds(89, 11, 335, 78);
        root.getContentPane().add(errorArea);
        errorArea.setLineWrap(true);
        errorArea.setText(message);

        root.setVisible(true);

    }

}
