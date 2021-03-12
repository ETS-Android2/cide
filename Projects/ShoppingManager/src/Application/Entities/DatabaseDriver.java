package Application.Entities;

/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-12

    DESCRIPTION
    
*/

import Data.BDManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Carlos Pomares
 */

public class DatabaseDriver {

    private Connection conn;
    private BDManager bdManager;

    public DatabaseDriver() throws Exception {
        bdManager = new BDManager();
        conn = bdManager.createConnection();
    }

    /*
     *
     * CLIENTES
     *
     * */

    // TODO Obtener nuevo ID de cliente
    public int obtenerNuevoIDCliente() {
        return 0;
    }

    // TODO Buscar un cliente por nombre
    public List<Client> buscarClientePorNombre(String nom) {
        return new ArrayList<>();
    }

    // TODO Agregar cliente
    public void agregarCliente(Client c) {}

    /*
     *
     * PRODUCTOS
     *
     * */

    // TODO Buscar producto por nombre

    // TODO Agregar producto

    /*
     *
     * ENCARGOS
     *
     * */

    // TODO Buscar encargos por cliente

    // TODO Crear encargo

    // TODO Eliminar encargo

}
