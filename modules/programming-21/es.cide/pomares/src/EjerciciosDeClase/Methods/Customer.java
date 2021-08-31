package EjerciciosDeClase.Methods;

/*

    Project     Programming21
    Package     EjerciciosDeClase.Methods
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-11-18

    DESCRIPTION
    (Parking Charges) A parking garage charges a $2.00 minimum
    fee to park for up to three hours. The garage charges an
    additional $0.50 per hour for each hour or part thereof in
    excess of three hours. The maximum charge for any given 24-hour
    period is $10.00. Assume that no car parks for longer than 24 hours
    at a time. Write an application that calculates and displays
    the parking charges for each customer who parked in the garage yesterday.
    You should enter the hours parked for each customer.
    The program should display the charge for the current customer and should
    calculate and display the running total of yesterday’s receipts.
    It should use the method calculateCharges to determine the charge for each customer.
    
*/

/**
 * @author Carlos Pomares
 */

public class Customer {

    private double hours;
    private double charges;

    public static double recaudacion = 0;

    public void setHours(double hours){
        this.hours = hours;
    }
    public double getHours() {
        return hours;
    }

    public double getCharges(){
        return this.charges;
    }

    public void calculateCharges(){
        if(hours >= 3){
            charges += 2;
            hours -=3;
            double hoursToCharge;
            hoursToCharge = hours /= 1;
            hoursToCharge *= 0.5;
            charges += hoursToCharge;
            if(charges > 10){
                charges = 10;
            }
        } else {
            charges = 0;
        }
    }
    
    public static void getCustomers(Customer[] customers){
        for(Customer manolo : customers){
            manolo.calculateCharges();
            System.out.printf("\nHa estado durante %f horas, y le han cobrado %f", manolo.getHours(),manolo.getCharges());
            Customer.recaudacion += manolo.getCharges();
        }
    }

}