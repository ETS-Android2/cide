package computer;

import computer.brands.Apple.AppleModel;
import computer.brands.Apple.AppleModels;
import computer.brands.Brands;
import computer.brands.Models;
import core.internal.processor.Processor;
import core.internal.ram.Ram;
import core.internal.storage.Drive;
import core.utilities.os.OS;
import os.OperativeSystem;
import os.microsoft.Windows;

import java.util.ArrayList;

public class Computer implements ComputerCore {

    private boolean computerState = false;

    private Brands computerBrand;
    private Models computerModel;
    private OperativeSystem computerOS;
    private Processor computerProcessor;
    protected ArrayList<Ram> installedRam;
    protected ArrayList<Drive> installedDrives;

    public Computer(){
        this.computerBrand = Brands.APPLE;
        this.computerModel = new AppleModel(AppleModels.PRO,2013);
        this.computerProcessor = new Processor();
        this.installedRam.add(new Ram(this));
        this.installedDrives.add(new Drive());
        installOperativeSystem(new Windows());
    }

    public Computer(Brands computerBrand,Models computerModel, OperativeSystem computerOS, Processor computerProcessor, ArrayList<Ram> computerRam,ArrayList<Drive> computerDrives){
        this.computerBrand = computerBrand;
        this.computerModel = computerModel;
        setComputerProcessor(computerProcessor);
        setInstalledRam(computerRam);
        setInstalledDrives(computerDrives);
        installOperativeSystem(computerOS);
    }

    public void setComputerProcessor(Processor computerProcessor) {
        this.computerProcessor = computerProcessor;
    }

    public void setInstalledRam(ArrayList<Ram> installedRam) {
        this.installedRam = installedRam;
    }

    public void setInstalledDrives(ArrayList<Drive> installedDrives) {
        this.installedDrives = installedDrives;
    }

    @Override
    public boolean startComputer() {
        return false;
    }

    @Override
    public boolean stopComputer() {
        return false;
    }

    @Override
    public boolean installOperativeSystem(OS os) {
        return false;
    }
}
