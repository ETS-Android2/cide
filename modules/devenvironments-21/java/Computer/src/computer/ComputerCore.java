package computer;

import core.utilities.os.OS;

public interface ComputerCore {

    boolean startComputer();
    boolean stopComputer();
    boolean installOperativeSystem(OS os);

}
