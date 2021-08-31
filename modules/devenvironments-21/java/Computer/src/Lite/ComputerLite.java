package Lite;

/*

    Project     ED21
    Package     Lite

    Version     1.0
    Author      Carlos Pomares
    Date        2020-12-08

    DESCRIPTION

*/

/**
 * @author Carlos Pomares
 */

public class ComputerLite {
    public static void main(String[] args) {

        // Computer
        Computer c1 = new Computer("Dell UltraLight",4096,100000);

        // Windows
        OperativeSystem windows = new OperativeSystem("Windows",
                "2004",
                "x86_64",
                false,
                20,
                2);

        // Applications
        Application s1,s2;

        s1 = new Application("Word","1.0",
                0.5f,1);

        s2 = new Application("Affinity Photo", "2.5",
                0.2f,0.6f);


        try {

            // Install Operative System in the Computer.
            c1.installOperatingSystem(windows);

            // Wake up the computer and assign the system on RAM.
            c1.startComputer();

            // Install Applications in Operative System.
            windows.installApplication(s1);
            windows.installApplication(s2);

            /*
                Opening applications will assign RAM on the system.
                Closing applications will release RAM on the system.
             */
            windows.openApplication(windows.getOsSoftware().get(windows.getOsSoftware().indexOf(s1)));
            windows.openApplication(windows.getOsSoftware().get(windows.getOsSoftware().indexOf(s2)));
            // windows.closeApplication(windows.getOsSoftware().get(windows.getOsSoftware().indexOf(s1)));
            // windows.closeApplication(windows.getOsSoftware().get(windows.getOsSoftware().indexOf(s2)));

            // Uninstalling by given application
            windows.uninstallApplication(windows.getOsSoftware().get(windows.getOsSoftware().indexOf(s2)));


        } catch (Exception e){
            // If an error occurs, will be caught here.
            System.out.println(e.getMessage());
        }

        // Before formatting.
        System.out.println("Before formatting.");
        System.out.println(c1.toString() + "\n\n");

        c1.formatSystem();

        // After formatting.
        System.out.println("After formatting.");
        System.out.println(c1.toString() + "\n\n");

    }
}
