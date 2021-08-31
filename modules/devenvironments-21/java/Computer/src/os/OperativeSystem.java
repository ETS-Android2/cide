package os;

import core.app.Application;
import core.internal.ram.Ram;
import core.internal.storage.Drive;
import core.utilities.os.OS;

import java.util.ArrayList;

public abstract class OperativeSystem implements OS {

    protected String osName;
    protected String osVersion;
    protected String osArchitecture;
    protected boolean isOnlyCommand;
    protected ArrayList<Drive> osDrives;
    protected ArrayList<Ram> osRam;
    protected ArrayList<Application> osApplications;

    @Override
    public String toString() {
        return "OperativeSystem{" +
                "osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osArchitecture='" + osArchitecture + '\'' +
                ", isOnlyCommand=" + isOnlyCommand +
                ", osDrives=" + osDrives +
                ", osRam=" + osRam +
                ", osApplications=" + osApplications +
                '}';
    }
}
