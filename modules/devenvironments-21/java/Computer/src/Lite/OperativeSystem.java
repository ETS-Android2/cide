package Lite;

/*

    Project     ED21
    Package     Lite

    Version     1.0
    Author      Carlos Pomares
    Date        2020-12-08

    DESCRIPTION
    An Operating System that manages the hardware and software that contains.
    Allows the user to interact with it.

*/

/**
 * @author Carlos Pomares
 */

import java.util.ArrayList;

public class OperativeSystem {

    // Operative System name
    private String osName;
    // Operative System version
    private String osVersion;
    // Operative System architecture
    private String osArchitecture;
    // If the operative system is only command.
    private boolean isOnlyCommand;
    // Operative System hard disk requirement.
    private int osSpaceRequirement;
    // Operative System ram memory requirement.
    private int osRamMemoryRequirement;
    // System state
    private boolean systemState;
    // Operative System list of installed applications.
    private ArrayList<Application> osSoftware;
    // Operative System assigned hardware (Computer)
    private Computer osComputer;


    /**
     *
     * Sets the initial values of the Operative System data.
     *
     * @param osName assigns the name of the OS.
     * @param osVersion assigns the version of the OS.
     * @param osArchitecture assigns the architecture of the OS.
     * @param isOnlyCommand assigns if the OS is in command mode.
     * @param osSpaceRequirement assigns the Hard drive requirements of the OS.
     * @param osRamMemoryRequirement assigns the RAM Memory requirements of the OS.
     */
    public OperativeSystem(String osName, String osVersion, String osArchitecture, boolean isOnlyCommand, int osSpaceRequirement, int osRamMemoryRequirement) {
        this.osName = osName;
        this.osVersion = osVersion;
        this.osArchitecture = osArchitecture;
        this.isOnlyCommand = isOnlyCommand;
        this.osSpaceRequirement = osSpaceRequirement;
        this.osRamMemoryRequirement = osRamMemoryRequirement;
        osSoftware = new ArrayList<Application>();
    }

    /**
     *
     * This method will set the computer that install the Operative System.
     *
     * @param computer the computer that installs the operative system.
     */
    public void setComputer(Computer computer){
        this.osComputer = computer;
    }

    /**
     * This method will start the system and assign the RAM requirement on the computer RAM.
     */
    public void startSystem(){
        this.osComputer.updateRamSpace(getOsRamMemoryRequirement(),"-");
        this.systemState = true;
    }

    /**
     * This method will release the RAM of the operative system in the computer RAM.
     */
    public void stopSystem(){
        this.osComputer.updateRamSpace(getOsRamMemoryRequirement(),"+");
        this.systemState = false;
    }

    /**
     *
     * This method allows the computer to install applications in the system.
     *
     * @param app the application object that will be installed.
     * @throws Exception if the same application is already installed on the system.
     */
    public void installApplication(Application app) throws Exception {
        if(this.osSoftware.contains(app)){
            throw new Exception("App already exists.");
        } else if(checkDiskSpace(app.getSoftwareSpaceRequirements())){
            this.osComputer.updateDriveSpace(app.getSoftwareSpaceRequirements(),"-");
            this.osSoftware.add(app);
        } else {
            throw new Exception("Error.");
        }
    }

    /**
     *
     * This method allows the computer to unistall an application that it is in the OS.
     *
     * @param app the application object that will be uninstalled.
     * @throws Exception if the application object doesn't exists on the system.
     */
    public void uninstallApplication(Application app) throws Exception {
        if(this.osSoftware.contains(app)){
            if(app.isOpen()){
                this.closeApplication(app);
            }
            this.osComputer.updateDriveSpace(app.getSoftwareSpaceRequirements(), "+");
            this.osSoftware.remove(app);
        } else {
            throw new Exception("Software isn't installed.");
        }
    }

    /**
     *
     * This method allows the operative system to open an application and assign
     * the RAM space requirement on the computer hardware.
     *
     * @param app the application that will be opened.
     * @throws Exception if the application needs more RAM memory than the actual free space.
     */
    public void openApplication(Application app) throws Exception {
        if(checkRamSpace(app.getSoftwareRamMemoryRequirements())){
            app.openApp();
            this.osComputer.updateRamSpace(app.getSoftwareRamMemoryRequirements(),"-");
        } else {
            throw new Exception("RAM requirement not satisfied.");
        }
    }

    /**
     *
     * This method will close an application and release his RAM requirement on
     * the computer hardware.
     *
     * @param app the application that will be closed.
     * @throws Exception if the application is already closed.
     */
    public void closeApplication(Application app) throws Exception{
        app.closeApp();
        this.osComputer.updateRamSpace(app.getSoftwareRamMemoryRequirements(),"+");
    }

    /**
     *
     * This method allows the OS to check if the spaceToBeSet fits on the hard drive.
     *
     * @param spaceToBeSet the space that will be set on the computer hard drive.
     * @return boolean if it fits on the hard drive.
     */
    private boolean checkDiskSpace(int spaceToBeSet){
        return spaceToBeSet < this.osComputer.getMaxHardDisk() && (this.osComputer.getCurrentHardDisk() + spaceToBeSet) <= this.osComputer.getMaxHardDisk();
    }

    /**
     *
     * This method allows the OS to check if the spaceToBeSet fits on the RAM memory.
     *
     * @param spaceToBeSet the space that will be set on the computer RAM memory.
     * @return boolean if it fits on the RAM memory.
     */
    private boolean checkRamSpace(int spaceToBeSet){
        return spaceToBeSet < this.osComputer.getMaxRamMemory() && (this.osComputer.getCurrentRamMemory() + spaceToBeSet) <= this.osComputer.getMaxRamMemory();
    }

    public String getOsName() {
        return osName;
    }
    public String getOsVersion() {
        return osVersion;
    }
    public String getOsArchitecture() {
        return osArchitecture;
    }
    public boolean isOnlyCommand() {
        return isOnlyCommand;
    }

    /**
     * @return the Hard drive requirement in MB.
     */
    public int getOsSpaceRequirement() {
        return osSpaceRequirement * 1024;
    }

    /**
     * @return the RAM memory requirement in MB.
     */
    public int getOsRamMemoryRequirement() {
        return osRamMemoryRequirement * 1024;
    }
    public ArrayList<Application> getOsSoftware() {
        return osSoftware;
    }

}
