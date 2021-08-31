package core.utilities.os;


import computer.Computer;
import core.app.Application;

public interface OS {
    boolean formatComputer(Computer cp);
    boolean installApplication(Application app);
    boolean uninstallApplication(Application app);
}
