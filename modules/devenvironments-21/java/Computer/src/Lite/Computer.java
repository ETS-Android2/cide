package Lite;

/*

    Project     ED21
    Package     Lite

    Version     1.0
    Author      Carlos Pomares
    Date        2020-12-08

    DESCRIPTION
    The physical part of the system that have the Hard drive and RAM Memory.

*/

/**
 * @author Carlos Pomares
 */

import java.util.ArrayList;

public class Computer {

    // Name of the computer.
    private String name;
    // Max memory on computer.
    private int maxRamMemory;
    // Max storage on disk.
    private int maxHardDisk;
    // Variable ram, specifies the actual memory in free in the system.
    private int ramMemory;
    // Variable disk space, specifies the actual disk space free.
    private int hardDisk;
    // Actual state of the computer
    private boolean computerState;
    // Variable containing the operative system of the computer.
    private OperativeSystem operativeSystem;

    /**
     *
     * This is the constructor and sets the initial values of the computer.
     *
     * @param name is the name of the computer.
     * @param ramMemory is the maximum space of the RAM Memory.
     * @param hardDisk is the maximum space of the Hard Drive.
     */
    public Computer(String name, int ramMemory, int hardDisk) {
        this.name = name;
        this.maxRamMemory = ramMemory;
        this.maxHardDisk = hardDisk;
        this.ramMemory = maxRamMemory;
        this.hardDisk = maxHardDisk;
    }

    /**
     *
     * This method allows a computer to install an Operative System, this method
     * will set the operativeSystem variable and asign the drive space requirement
     * of the OS.
     *
     * @param os is the OS that will be installed on the computer.
     * @throws Exception is there is an Operative System already installed will throw and Exception.
     */
    public void installOperatingSystem(OperativeSystem os) throws Exception{
        if(this.operativeSystem == null && this.hardDisk >= os.getOsSpaceRequirement()){
            // Assign physic computer to an operative system.
            os.setComputer(this);
            // Assign the operative system in the
            this.operativeSystem = os;
            // Update the drive space in the computer.
            updateDriveSpace(os.getOsSpaceRequirement(),"-");
        } else {
            // User cannot install more than one operative system in the computer.(Rule)
            throw new Exception("Existing operating system.");
        }
    }

    /**
     *
     * This method allows the OS to interact with the Hard Drive to apply modifications.
     *
     * @param spaceToUpdate is the space that gill ve added or subtracted on the Hard Drive.
     * @param op is the operation that will be executed.
     */
    public void updateDriveSpace(int spaceToUpdate, String op){
        switch (op){
            case "+" -> {
                this.hardDisk += spaceToUpdate;
            }
            case "-" -> {
                this.hardDisk -= spaceToUpdate;
            }
        }
    }

    /**
     *
     * This method allows the OS to interact with the computer hardware and modify it.
     *
     * @param spaceToUpdate is the space to be updated in the RAM component.
     * @param op is the operation that will be executed on the RAM, can be + or -.
     */
    public void updateRamSpace(int spaceToUpdate, String op){
        switch (op){
            case "+" -> {
                this.ramMemory += spaceToUpdate;
            }
            case "-" -> {
                this.ramMemory -= spaceToUpdate;
            }
        }
    }

    /**
     *  This method will reset three main components of variable data of the computer.
     *  Will set his specifications to maximum, representing the formatted system.
     */
    public void formatSystem(){
        this.operativeSystem = null;
        this.hardDisk = this.maxHardDisk;
        this.ramMemory = this.maxRamMemory;
    }

    /**
     *
     * This method opens the computer if it's closed and start the system.
     *
     * @throws Exception if the computer is already opened.
     */
    public void startComputer() throws Exception {
        if(this.computerState){
            throw new Exception("Computer is already open.");
        } else if(this.operativeSystem != null){
            this.operativeSystem.startSystem();
            this.computerState = true;
        } else {
            throw new Exception("No operative system installed.");
        }
    }

    /**
     *
     * This method invokes the close of the computer and will stop the system and
     * set the computerState to false.
     *
     * @throws Exception if the computer is already closed.
     */
    public void stopComputer() throws Exception {
        if(!this.computerState){
            throw new Exception("Computer is closed.");
        } else {
            this.operativeSystem.stopSystem();
            this.computerState = false;
        }
    }

    public OperativeSystem getOperativeSystem(){
        return this.operativeSystem;
    }

    public int getCurrentRamMemory() {
        return ramMemory;
    }

    public int getCurrentHardDisk() {
        return hardDisk;
    }

    public int getMaxRamMemory() {
        return maxRamMemory;
    }

    public int getMaxHardDisk() {
        return maxHardDisk;
    }

    @Override
    public String toString() {

        // Array of applications temporally
        ArrayList<Application> applications;
        String operativeSystemName = "";

        // If operative system exists will show an application objects.
        if(operativeSystem != null){
            applications = operativeSystem.getOsSoftware();
            operativeSystemName = operativeSystem.getOsName();
        } else {
            // Else if do not exists an operative system in the computer will show null
            applications = null;
            operativeSystemName = null;
        }

        return "Computer{" +
                "name='" + name + '\'' +
                ", maxRamMemory=" + maxRamMemory +
                ", maxHardDisk=" + maxHardDisk +
                ", Free ramMemory=" + ramMemory +
                ", Free hardDisk=" + hardDisk +
                ", computerState=" + computerState +
                ", operativeSystem=" + operativeSystemName +
                ", Applications=" + applications +
                '}';
    }
}
