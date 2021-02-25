package Services;

/*

    Project     Programming21
    Package     Services    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

import Util.ChatForm;

import javax.swing.*;
import java.awt.*;

/**
 * @author Carlos Pomares
 */

public class ErrorWindow {

    private static JFrame root = new JFrame();
    private static JTextArea errorArea = new JTextArea();
    private static JLabel lblNewLabel = new JLabel("Error:");

    public static void run(String message) {

        root = new JFrame();
        root.setBounds(100, 100, 450, 139);
        root.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        root.getContentPane().setLayout(null);

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
