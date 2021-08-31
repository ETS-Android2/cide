package Lite;

/*

    Project     ED21
    Package     Lite

    Version     1.0
    Author      Carlos Pomares
    Date        2020-12-08

    DESCRIPTION
    An Application that will be part of the Operative System.

*/

/**
 * @author Carlos Pomares
 */

public class Application {

    // The name of the software.
    private String softwareName;
    // The version of the software.
    private String softwareVersion;
    // The space requirement of the software on the hard drive.
    private float softwareSpaceRequirements;
    // The space requirement of the software on the RAM memory.
    private float softwareRamMemoryRequirements;
    // The current state of the application, if is open or closed.
    private boolean isOpen = false;

    /**
     *
     * This constructor will set the initial data of the application.
     *
     * @param softwareName Sets the name of the software.
     * @param softwareVersion Sets the version of the software.
     * @param softwareSpaceRequeriments Sets the space requirement in the hard drive.
     * @param softwareRamMemoryRequeriment Sets the space requirement in the RAM memory.
     */
    public Application(String softwareName, String softwareVersion, float softwareSpaceRequeriments, float softwareRamMemoryRequeriment) {
        this.softwareName = softwareName;
        this.softwareVersion = softwareVersion;
        this.softwareSpaceRequirements = softwareSpaceRequeriments;
        this.softwareRamMemoryRequirements = softwareRamMemoryRequeriment;
    }

    /**
     *
     * This method opens the application.
     *
     * @throws Exception if the application is already opened.
     */
    public void openApp() throws Exception {
        if(this.isOpen){
            throw new Exception("App is already open.");
        } else{
            this.isOpen = true;
        }
    }

    /**
     *
     * This method will close the application.
     *
     * @throws Exception if the application is already closed.
     */
    public void closeApp() throws Exception {
        if(!this.isOpen){
            throw new Exception("App is already open.");
        } else{
            this.isOpen = false;
        }
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    /**
     *
     * @return Returns the integer value of the drive space requirement in MB.
     */
    public int getSoftwareSpaceRequirements() {
        return (int)(softwareSpaceRequirements * 1024);
    }

    /**
     *
     * @return Returns the integer value of the RAM space requirement in MB.
     */
    public int getSoftwareRamMemoryRequirements() {
        return (int)(softwareRamMemoryRequirements * 1024);
    }
}
