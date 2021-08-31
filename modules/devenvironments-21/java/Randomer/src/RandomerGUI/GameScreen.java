package RandomerGUI;

/*

    Project     ED21
    Package     Gui    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-10

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

import RandomerUtils.RandomerGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class GameScreen extends JFrame {

    private JPanel mainPanel;

    private JTextField numberField;
    private JButton checkNumberButton;

    private JList insertedNumbersList;
    private JLabel hiddenNumberLabel;
    private JLabel numberOfTriesLabel;
    private JLabel hintLabel;
    private JLabel actualBalanceLabel;

    private ArrayList<Integer> introducedNumbersArray;
    private DefaultListModel<Integer> introducedNumbersModel;

    private int actualTries;
    private int maximumTries;
    private int hiddenNumber;
    private int actualBalance;
    private boolean gameType;

    /**
     *
     * Initializes the game and set the maximum tries given
     * by the ModeScreen and the Game type.
     *
     * @param maximumTries the user tries.
     * @param gameType the game type.
     */
    public GameScreen(int maximumTries, boolean gameType){
        // Initialize screen.
        super("Adivina el número");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        // Set properties values.
        this.maximumTries = maximumTries;
        this.actualTries = maximumTries;
        this.gameType = gameType;
        this.actualBalance = 20;
        startGame();

        checkNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkNumber(e);
            }
        });

    }

    /**
     *
     * Will establish some game properties and assign the values.
     *
     */
    private void startGame(){

        this.actualBalance -= 1;
        this.introducedNumbersArray = new ArrayList<>();
        this.introducedNumbersModel = new DefaultListModel<Integer>();

        this.insertedNumbersList.setModel(introducedNumbersModel);

        refreshList();

        this.hiddenNumberLabel.setText("**");
        this.actualBalanceLabel.setText(String.valueOf(this.actualBalance) + "€");

        if(gameType){
            this.hiddenNumber = RandomerGenerator.generateNumber(99);
        } else {
            this.hiddenNumber = RandomerGenerator.generateNumber(9);
        }

        this.numberOfTriesLabel.setText(Integer.toString(this.actualTries));

        //System.out.println("GAME TYPE: " + gameType + " HIDDEN NUMBER: " + hiddenNumber);

    }

    /**
     *
     * Checks if the number introduced and the hidden number are equals
     * and check some other values as actual tries of the user and the balance.
     *
     * @param e the action event listener.
     */
    private void checkNumber(ActionEvent e){

        int number;
        int optionPanel;

        try {
            number = Integer.parseInt(numberField.getText());

            if(RandomerGenerator.checkNumber(number,this.hiddenNumber)){
                this.actualBalance += 5;
                this.hiddenNumberLabel.setText(Integer.toString(this.hiddenNumber));
                String message = String.format("Winner. Added 5€\nHidden number: %d\nNew game for 1€?",this.hiddenNumber);
                System.out.println();
                optionPanel = JOptionPane.showConfirmDialog(this,message);
                switch (optionPanel){
                    case 0 -> {
                        this.actualTries = maximumTries;
                        startGame();
                    }
                    case 1,2 -> this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
                }
            } else if(this.actualTries == 0){
                String msg = String.format("Game over, no tries remaining.\nHidden number: " + this.hiddenNumber + "\nNew game for 1€?");
                optionPanel = JOptionPane.showConfirmDialog(this,msg);
                switch (optionPanel){
                    case 0 -> {
                        this.actualTries++;
                        startGame();
                    }
                    case 1,2 -> this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
                }
            } else if(this.actualTries == 0 && checkBalance()){
                String msg = "Game Over.\nRestart game?";
                optionPanel = JOptionPane.showConfirmDialog(this,msg);
                switch (optionPanel){
                    case 0 -> {
                        gameOver();
                        startGame();
                    }
                    case 1,2 -> this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
                }
            } else {
                this.actualTries--;
                this.numberOfTriesLabel.setText(Integer.toString(this.actualTries));
                this.hintLabel.setText(RandomerGenerator.generateHint(RandomerGenerator.generateHint(number,this.hiddenNumber)));
                this.introducedNumbersArray.add(number);
                refreshList();
            }
        } catch (Exception error){
            JOptionPane.showMessageDialog(this,"Error: " + error.getMessage());
        }


    }

    /**
     * Reset to 20 the actualBalance of the user.
     */
    private void gameOver(){
        this.actualBalance = 20;
    }

    /**
     *
     * Checks the balance of the user.
     *
     * @return true if the balance is 0 or lower.
     */
    private boolean checkBalance(){
        return this.actualBalance <= 0;
    }

    /**
     *
     * Clear the arraylist of the list and waits for new values.
     *
     */
    private void refreshList(){
        this.introducedNumbersModel.removeAllElements();
        for(Integer number : introducedNumbersArray){
            this.introducedNumbersModel.addElement(number);
        }
    }



}
