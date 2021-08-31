package RandomerGUI;

/*

    Project     ED21
    Package     Gui    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-10

    DESCRIPTION
    
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * @author Carlos Pomares
 */

public class ModeScreen extends JFrame {

    // SWING Elements
    private JTextField numberOfTries;
    private JButton startGame;
    private JPanel mainPanel;
    private JRadioButton nineGameButton;
    private JRadioButton nintiesGameButton;

    // Initialize GameScreen throught the ModeScreen
    private GameScreen game;
    private boolean gameType = false;

    /**
     *
     * ModeScreen initializes a window that allows the user to select
     * between Nine Game or Ninties Game
     *
     */
    public ModeScreen(){


        super("Randomer");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        // Listener to the startGame button in Swing .form
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(e);
            }
        });

        // Create button group that has the two modes.
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.nineGameButton);
        buttonGroup.add(this.nintiesGameButton);

        // Default selected Nine Game.
        this.nineGameButton.setSelected(true);

    }

    /**
     *
     * Initialize the Game with the selected settings.
     * It set the settings for the GameScreen component.
     *
     * @param e the action event.
     */
    private void startGame(ActionEvent e){
        try {

            // SET the type of the game.
            if(this.nineGameButton.isSelected()){
                this.gameType = false;
            } else if(this.nintiesGameButton.isSelected()){
                this.gameType = true;
            }

            // Instace of the GameScreen.
            game = new GameScreen(Integer.parseInt(this.numberOfTries.getText()),this.gameType);
            game.setSize(600,400);
            game.setLocationRelativeTo(null);
            game.setResizable(false);

            // Dont show the ModeScreen and show the GameScreen
            this.setVisible(false);
            game.setVisible(true);

        } catch (Exception error){
            // If error occurs shows up with a message dialog.
            JOptionPane.showMessageDialog(this,error.getMessage());
        }

    }

}
