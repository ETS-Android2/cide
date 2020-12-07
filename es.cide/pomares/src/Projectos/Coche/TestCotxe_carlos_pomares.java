package Projectos.Coche;

/*

    Project     Programming21
    Package     Projectos.Coche    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2020-12-04

    DESCRIPTION
    
*/

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Carlos Pomares
 */


public class TestCotxe_carlos_pomares {

    final private static ArrayList<Cotxe_carlos_pomares> COTXES = new ArrayList<>();
    final private static ArrayList<String> ORDRES = new ArrayList<>();
    final private static ArrayList<String> ERRORS = new ArrayList<>();
    final private static Scanner USER_IN = new Scanner(System.in);

    private static Cotxe_carlos_pomares vehicleSeleccionat;

    private static void menuPrincipal(){

        boolean exit = false;

        String[] opcions = new String[]{"Crear vehicle","Generar vehicles","Seleccionar vehicle","Conduir vehicle","Sortir"};

        System.out.printf("========= %s =========\n","Bienvenido");

        while(!exit){

            if(vehicleSeleccionat != null){

                System.out.print("\n\t--------- Vehicle seleccionat ---------\n");

                System.out.printf("\t%-6s %-15s %-10s %-20s %-10s\n",
                        "Nombre","Marca",
                        "Model","Tipus canvi",
                        "Descapotable");

                System.out.printf("\t%-6d %-15s %-10s %-20s %-10b\n",
                        (COTXES.indexOf(vehicleSeleccionat) + 1),vehicleSeleccionat.getMarca(),
                        vehicleSeleccionat.getModel(),vehicleSeleccionat.getTipusCanvi(),
                        vehicleSeleccionat.getSiEsDescapotable());

                System.out.print("\t---------------------------------------\n");
            }

            System.out.printf("\n\t%-15s %-30s\n",
                    "Nombre","Opcio");

            for (int i = 0; i < opcions.length ; i++) {
                if(vehicleSeleccionat != null){
                    System.out.printf("\t%-15d %-30s\n",(i + 1),opcions[i]);
                } else {
                    if(!"Conduir vehicle".equals(opcions[i])){
                        System.out.printf("\t%-15d %-30s\n",(i + 1),opcions[i]);
                    }
                }
            }

            System.out.print("\n\tOrdre: ");

            try {
                switch (Integer.parseInt(USER_IN.nextLine())){
                    case 1 -> crearVehicle();
                    case 2 -> generarVehicles();
                    case 3 -> seleccionarVehicle();
                    case 4 -> {
                        if(vehicleSeleccionat != null){
                            conduirVehicle();
                        }
                    }
                    case 5 -> exit = true;
                    default -> System.out.println("Selecciona una opcio.");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.print("========= END MAIN MENU =========\n");
    }

    // TODO Crear vehicle
    private static void crearVehicle(){
        System.out.println("No implementat.");
    }

    // TODO Generar vehicles
    private static void generarVehicles(){
        // Default vehicles
        Cotxe_carlos_pomares lamborghini = new Cotxe_carlos_pomares("Lamborghini","Gallardo",TipusCanvi.CanviAutomatic,false);
        Cotxe_carlos_pomares fiat = new Cotxe_carlos_pomares("Fiat","500S",TipusCanvi.CanviManual,false);
        Cotxe_carlos_pomares mercedes = new Cotxe_carlos_pomares("Mercedes","McLaren",TipusCanvi.CanviAutomatic,true);
        COTXES.add(lamborghini);
        COTXES.add(fiat);
        COTXES.add(mercedes);
    }

    // TODO Seleccio
    private static void seleccionarVehicle(){

        System.out.print("\n========= Seleccio de vehicle =========\n");

        System.out.printf("\n\t%-6s %-15s %-10s %-20s %-10s\n",
                "Nombre","Marca",
                "Model","Tipus canvi",
                "Descapotable");

        // Mostrar vehicles
        for(Cotxe_carlos_pomares vehicle : COTXES){
            System.out.printf("\t%-6d %-15s %-10s %-20s %-10b\n",
                    (COTXES.indexOf(vehicle) + 1),vehicle.getMarca(),
                    vehicle.getModel(),vehicle.getTipusCanvi(),
                    vehicle.getSiEsDescapotable());
        }

        // Donar seleccio
        int seleccio;

        System.out.print("\n\tOrdre: ");
        seleccio = Integer.parseInt(USER_IN.nextLine());

        for (int i = 0; i < COTXES.size(); i++) {
            if((seleccio - 1) == i){
                vehicleSeleccionat = COTXES.get(i);
                ORDRES.clear();
                ERRORS.clear();
            }
        }

        System.out.print("\n========= END SELECT MENU =========\n");
    }

    // TODO Conduccio
    private static void conduirVehicle(){

        String[] opcions = new String[]{
            "Arrancar","Aturar","Accelerar","Frenar","Pujar marxa","Baixar marxa","Configurar vehicle","Borrar ordres i Errors","Sortir"
        };

        boolean exit = false;

        System.out.print("\n========= Conduccio de Vehicle =========\n");

        while(!exit){
            try {

                informacioVehicle();
                informacioEstadistiques();
                informacioComponents();
                historialOrdres();
                if(ERRORS.size() != 0){
                    informeErrors();
                }

                // Mostrar opcions
                for (int i = 0; i < opcions.length ; i++) {
                    if(vehicleSeleccionat.comprovaMotor() == EstatsMotorCotxe.EnMarxa){
                        if(!"Arrancar".equals(opcions[i])) {
                            if(vehicleSeleccionat.tipuscanvi == TipusCanvi.CanviManual){
                                System.out.printf("\t(%d) %s",
                                        (i + 1), opcions[i]);
                            } else {
                                if(!"Pujar marxa".equals(opcions[i]) && !"Baixar marxa".equals(opcions[i])){
                                    System.out.printf("\t(%d) %s",
                                            (i + 1), opcions[i]);
                                }
                            }

                        }
                    } else {
                        if("Arrancar".equals(opcions[i]) || "Configurar vehicle".equals(opcions[i]) || "Sortir".equals(opcions[i])) {
                            System.out.printf("\t(%d) %s",
                                    (i + 1), opcions[i]);
                        }
                    }

                }

                System.out.print("\n\n\tOrdre: ");

                switch (Integer.parseInt(USER_IN.nextLine())){
                    case 1 -> {
                        ORDRES.add("Arrancar motor.");
                        vehicleSeleccionat.arrancarMotor();
                    }
                    case 2 -> {
                        ORDRES.add("Aturar.");
                        vehicleSeleccionat.aturarMotor();
                    }
                    case 3 -> {
                        ORDRES.add("Accelerar.");
                        vehicleSeleccionat.accelerar();
                    }
                    case 4 -> {
                        ORDRES.add("Frenar.");
                        vehicleSeleccionat.frenar();
                    }
                    case 5 -> {
                        ORDRES.add("Pujar marxa.");
                        vehicleSeleccionat.incrementarMarxa();
                    }
                    case 6 -> {
                        ORDRES.add("Baixar marxa.");
                        vehicleSeleccionat.decrementarMarxa();
                    }
                    case 7 -> {
                        ORDRES.add("Configurar vehicle.");
                        configuracioVehicle();
                    }
                    case 8 -> {
                        ORDRES.clear();
                        ERRORS.clear();
                    }
                    case 9 -> exit = true;
                    default -> System.out.println("Opcio incorrecte.");
                }

            } catch (Exception e){
                ERRORS.add(e.getMessage());
            }
        }

        System.out.print("\n========= END MODULE =========\n");
    }

    // TODO Configuracio
    private static void configuracioVehicle(){
        System.out.println("No implementat.");
    }

    // TODO INFORMACIONS
    private static void informacioVehicle(){
        // Mostrar vehicle
        // MARCA - MODEL - TIPUS CANVI - DESCAPOTABLE
        System.out.print("\n\t------------------------ VEHICLE ---------------------------");

        System.out.printf("\n\t%-15s %-15s %-15s %-15s",
                "MARCA","MODEL",
                "TIPUS CANVI","DESCAPOTABLE");

        System.out.printf("\n\t%-15s %-15s %-15s %-15b",
                vehicleSeleccionat.getMarca(),vehicleSeleccionat.getModel(),
                vehicleSeleccionat.getTipusCanvi(),vehicleSeleccionat.getSiEsDescapotable());

        System.out.print("\n\t------------------------------------------------------------");
    }
    private static void informacioEstadistiques(){
        // Mostrar estadisticas
        // VELOCITAT - REVOLUCIONS - MARXA ACTUAL - REVERSE
        System.out.print("\n\t--------------------- ESTADISTIQUES ------------------------");

        System.out.printf("\n\t%-15s %-15s %-15s %-15s",
                "VELOCITAT","REVOLUCIONS",
                "MARXA ACTUAL","ESTATUS");

        System.out.printf("\n\t%-15s %-15s %-15s %-15s",
                vehicleSeleccionat.getVelocitat(),vehicleSeleccionat.getRevolucions(),
                vehicleSeleccionat.getMarxaActual(),vehicleSeleccionat.comprovaMotor());

        System.out.print("\n\t------------------------------------------------------------");
    }
    private static void informacioComponents(){
        // CONFIGURACIO (SI)
        // AIRE ACON - VELOCITAT
        // CAPOTA - PUESTA
        // LIMPIA - VELOCITAT
        System.out.print("\n\t---------------------- CONFIGURACIO ------------------------");

        System.out.printf("\n\t%-20s %-15s",
                "COMPONENT","VELOCITAT");

        for(Map.Entry<String,Integer> entry : vehicleSeleccionat.getComponents().entrySet()){
            System.out.printf("\n\t%-20s %-15d",
                    entry.getKey(),entry.getValue());
        }

        System.out.print("\n\t------------------------------------------------------------\n\n");
    }
    private static void historialOrdres(){
        // ORDRE ANTERIOR
        System.out.print("\t-------------------------- ORDRES --------------------------");

        for (int i = 0; i < TestCotxe_carlos_pomares.ORDRES.size(); i++) {
            System.out.printf("\n\t%-10s %-15s",
                    (i+1), TestCotxe_carlos_pomares.ORDRES.get(i));
        }

        System.out.print("\n\t------------------------------------------------------------\n\n");

    }
    private static void informeErrors(){
        // Si hi ha errors en la conduccio surten
        System.out.print("\t-------------------------- ERRORS --------------------------");

        for (int i = 0; i < ERRORS.size(); i++) {
            System.out.printf("\n\t%-10s %-15s",
                    (i+1), ERRORS.get(i));
        }

        System.out.print("\n\t------------------------------------------------------------\n\n");
    }

    public static void main(String[] args) {
        menuPrincipal();
    }
}
