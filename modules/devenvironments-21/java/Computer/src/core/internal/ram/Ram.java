package core.internal.ram;

import computer.Computer;

public class Ram {

    private String manufacturer;
    private Computer computer;
    private String technology;
    private int maxCapacity;
    private int velocity;
    private int actualCapacity;

    public Ram(Computer computer){
        this.manufacturer = "Samsung";
        this.technology = "LPDDR4";
        this.maxCapacity = 8096;
        this.velocity = 2666;
        this.computer = computer;
    }

    public Ram(String manufacturer,String technology, int maxCapacity, int velocity, Computer computer){
        setComputer(computer);
        setManufacturer(manufacturer);
        setTechnology(technology);
        setMaxCapacity(maxCapacity);
        setVelocity(velocity);
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void addData(int dataToAdd) {
        if(this.actualCapacity > this.maxCapacity){
            this.actualCapacity += dataToAdd;
        }
    }

    public void deleteData(int dataToDelete){
        this.actualCapacity -= dataToDelete;
    }

}
