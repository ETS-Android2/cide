package os.microsoft;

import computer.Computer;
import core.app.Application;
import os.OperativeSystem;

import java.util.ArrayList;

final public class Windows extends OperativeSystem {

    private static boolean state = false;
    private static Windows windows;
    private static Computer computer;

    public Windows(){
        this.osName = "Windows 10";
        this.osVersion = "2004";
        this.osArchitecture = "x86_64";
        this.isOnlyCommand = false;
        this.osApplications = new ArrayList<Application>();
    }

    @Override
    public boolean formatComputer(Computer cp) {
        return false;
    }

    @Override
    public boolean installApplication(Application app) {
        return false;
    }

    @Override
    public boolean uninstallApplication(Application app) {
        return false;
    }
}
