package RandomerConsole;

/*

    Project     ED21
    Package     RandomerConsole
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-10

    DESCRIPTION
    
*/

import RandomerUtils.RandomerGenerator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Carlos Pomares
 */

public class RandomerConsole {

    // USER ERRORS
    final private static ArrayList<String> ERRORS = new ArrayList<>();

    // FACTORY SETTINGS
    final private int MACHINE_FACTORY_FOUNDS = 50;
    final private int USER_FACTORY_FOUNDS = 20;

    // INPUT OBJECT
    private static Scanner userIn;

    // GAME PROPERTIES
    private int userFounds;
    private int machineFounds;

    /**
     *
     * Configures and sets the default input, initializes game properties and proceed to mainMenu.
     *
     */
    public RandomerConsole(){
        userIn = new Scanner(System.in);

        this.userFounds = this.USER_FACTORY_FOUNDS;
        this.machineFounds = this.MACHINE_FACTORY_FOUNDS;

        menuPrincipal();
    }

    /**
     *
     * Is the main loop that allows the user access to start or reset the game, or exit.
     *
     */
    private void menuPrincipal(){

        // The flag
        boolean exit = false;

        // Main menu options
        String[] opcions = new String[]{"99 Numbers","9 Numbers","Reset game","Exit"};

        // Flag algorithm
        while(!exit){
            try {

                showOptions(opcions);

                if(ERRORS.size() != 0) errorBrief();

                System.out.print("\n\tOrder: ");

                switch (Integer.parseInt(userIn.nextLine())){
                    case 1 -> this.startGame(5,99);
                    case 2 -> this.startGame(5,9);
                    case 3 -> resetGame();
                    case 4 -> exit = true;
                }

            } catch (Exception e){
                ERRORS.add(e.getMessage());
            }
        }

    }

    /**
     *
     * Start game method that the user access to the game loop and can play.
     *
     * @param numberOfTries by default is 5 tries, but it can be changed to allow more tries.
     * @param maximumNumber is given by the selected game. Currently available Nine and Ninties game.
     * @throws Exception if exception occurs the game will catch it.
     */
    private void startGame(int numberOfTries,int maximumNumber) throws Exception {

        boolean success = false;
        int userTries = numberOfTries;
        int hiddenNumber = RandomerGenerator.generateNumber(maximumNumber);
        int userNumber = -1;

        String userHint = "";
        ArrayList<Integer> tries = new ArrayList<>();

        if(getFounds(this.userFounds) > 0){
            subtractFounds(1,"user");
            addFounds(1,"machine");
        }

        while (userTries > 0 && !success){
            try {

                playerBrief(userNumber,userTries,userHint,this.userFounds,"Playing");

                System.out.print("\n\n\t(1) Introducir número");

                System.out.print("\n\tOrder: ");

                // Introducir número, Mostrar pista
                if (Integer.parseInt(userIn.nextLine()) == 1) {
                    System.out.print("\tIntroduce el número: ");
                    userNumber = userIn.nextInt();
                    userTries--;
                    if (RandomerGenerator.checkNumber(userNumber, hiddenNumber)) {
                        subtractFounds(5,"machine");
                        addFounds(5,"user");
                        success = true;
                    }
                    tries.add(userNumber);
                    userHint = RandomerGenerator.generateHint(RandomerGenerator.generateHint(userNumber, hiddenNumber));
                }

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\n\n\tNumbers used: " + tries.toString());
        System.out.println("\tHidden number: " + hiddenNumber);

    }

    /**
     *
     * This method will reset all game counters and clear the exception arraylist.
     *
     */
    private void resetGame(){
        this.userFounds = USER_FACTORY_FOUNDS;
        this.machineFounds = MACHINE_FACTORY_FOUNDS;
        ERRORS.clear();
    }

    /**
     *
     * Allows the game to add founds to desired property.
     *
     * @param toAdd the number to add to property.
     * @param foundsObj the name of the property. [machine,user]
     */
    private void addFounds(int toAdd, String foundsObj) {
        if(foundsObj.equals("machine")){
            this.machineFounds += toAdd;
        } else {
            this.userFounds += toAdd;
        }
    }

    /**
     *
     * Allows the game to subtract founds to desired property.
     *
     * @param toSubtract the number to subtract to property.
     * @param foundsObj the name of the property. [machine,user]
     * @throws Exception if the property counter is less than 0 cannot subtract more coins and will throw an exception.
     */
    private void subtractFounds(int toSubtract, String foundsObj) throws Exception {
        if(foundsObj.equals("machine")){
            if(getFounds(this.machineFounds) <= 0) throw new Exception("Money threshold.");
            this.machineFounds -= toSubtract;
        } else {
            if(getFounds(this.userFounds) <= 0) throw new Exception("Money threshold.");
            this.userFounds -= toSubtract;
        }
    }

    /**
     *
     * Allows the game to get the founds of the given object.
     *
     * @param foundsObj the object to get the founds.
     * @return the value of the object.
     */
    private int getFounds(Integer foundsObj){
        return foundsObj;
    }

    /**
     *
     * This method allows the game to show the given information in a
     * better way by printing some formatted information.
     *
     * @param playerLastNumber the last player number.
     * @param playerTries the actual player tries.
     * @param hint the hint if exists.
     * @param userMoney the user founds.
     * @param status the currently game status.
     */
    private void playerBrief(int playerLastNumber, int playerTries, String hint,int userMoney, String status){

        System.out.print("\n\t------------------ PLAYER BRIEF ------------------\n");

        System.out.printf("\n\t%-15s \t%-15s",
                "Machine founds","User founds");

        System.out.printf("\n\t%-15d \t%-15d",
                getFounds(this.machineFounds),getFounds(this.userFounds));


        System.out.printf("\n\t%-15s \t%-20s \t%-10s \t%-30s",
                "Last Number","Remaining tries", "Status","Hint");

        System.out.printf("\n\t%-15d \t%-20d \t%-10s \t%-30s\n",
                playerLastNumber,playerTries,status,hint);


        System.out.print("\n\t--------------------------------------------------\n");

    }

    /**
     *
     * This method allows the game to show the errors in a better
     * way to represent with formatted text.
     *
     */
    private void errorBrief(){

        // Si hi ha errors en la conduccio surten
        System.out.print("\t-------------------------- ERRORS --------------------------");

        for (int i = 0; i < ERRORS.size(); i++) {
            System.out.printf("\n\t%-10s %-15s",
                    (i+1), ERRORS.get(i));
        }

        System.out.print("\n\t------------------------------------------------------------\n\n");

    }

    /**
     *
     * This methods allows the game to show up the given information
     * with a infinite data within an array.
     *
     * @param options the array of options.
     */
    private void showOptions(String[] options){

        int optionNumber = 1;

        System.out.print("\n\t--------------- OPTIONS ---------------\n");

        System.out.printf("\n\t%-15s \t%-15s",
                "Machine founds","User founds");

        System.out.printf("\n\t%-15d \t%-15d\n",
                getFounds(this.machineFounds),getFounds(this.userFounds));

        for(String option : options){
            System.out.printf("\n\t(%d) \t%s",
                    optionNumber,option);
            optionNumber++;
        }

        System.out.print("\n\n\t---------------------------------------\n");

    }

}
