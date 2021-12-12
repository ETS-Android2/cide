import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQL_carlos_pomares {
    
    public static void main(String[] args) throws Exception {
        
        String HOSTNAME = "127.0.0.1";
        String PORT = "16001";
        String DATABASE = "demo";
        String USERNAME = "root";
        String PASSWORD = "Cide2050";

        String URL = String.format(
            "jdbc:mysql://%s:%s/%s?allowPublicKeyRetrieval=true&useSSL=false",
            HOSTNAME,
            PORT,
            DATABASE
        );

        Connection connection = DriverManager.getConnection(
            URL,
            USERNAME,
            PASSWORD
        );

        
        // 1. Visualitzar número i nom de tots els departaments.
        System.out.println("Visualitzar número i nom de tots els departaments.");

        String deptQuery = "SELECT DEPTNO as id, DNAME as name FROM dept";

        Statement debtStmt = connection.createStatement();
        ResultSet debtRS = debtStmt.executeQuery(deptQuery);
        while (debtRS.next()) {
            System.out.println(
                String.format(
                    "Departamento Id={%d} Nombre={%s}",
                    debtRS.getInt("id"),
                    debtRS.getString("name")
                )
            );
        }
        debtRS.close();

        // 2. Modificar el nom d'un departament el qual el número (i nom) es passi(n) com a argument. No utilitzar sentències preparades. Visualitzar el nombre de files afectades.
        System.out.println("Modificar el nom d'un departament el qual el número (i nom) es passi(n) com a argument. No utilitzar sentències preparades. Visualitzar el nombre de files afectades.");

        // Pedir al usuario un departament
        System.out.println("Introdueix el departament que vols modificar");
        int deptNo = Integer.parseInt(System.console().readLine());

        // Pedir al usuario un nou nom
        System.out.println("Introdueix el nou nom del departament");
        String newName = System.console().readLine();

        int deptUpdateId = deptNo;
        String deptUpdateName = newName;
        
        String debtUpdateQuery = String.format(
            "UPDATE dept SET DNAME = '%s' WHERE DEPTNO = %d",
            deptUpdateName,
            deptUpdateId
        );

        Statement debtUpdateStmt = connection.createStatement();
        int debtUpdateRows = debtUpdateStmt.executeUpdate(debtUpdateQuery);
        System.out.println(
            String.format(
                "Filas afectadas: %d",
                debtUpdateRows
            )
        );
        debtUpdateStmt.close();

        // 3. Realitza l'exercici anterior amb sentències preparades.
        System.out.println("Realitza l'exercici anterior amb sentències preparades.");
        PreparedStatement debtUpdatePS = connection.prepareStatement("UPDATE dept SET DNAME = ? WHERE DEPTNO = ?");

        // Pedir al usuario un departament
        System.out.println("Introdueix el departament que vols modificar");
        deptNo = Integer.parseInt(System.console().readLine());

        // Pedir al usuario un nou nom
        System.out.println("Introdueix el nou nom del departament");
        newName = System.console().readLine();

        debtUpdatePS.setString(1, newName);
        debtUpdatePS.setInt(2, deptNo);
        debtUpdateRows = debtUpdatePS.executeUpdate();
        System.out.println(
            String.format(
                "Filas afectadas: %d",
                debtUpdateRows
            )
        );

        // 4. Realitza l'exercici anterior utilitzant transaccions.
        System.out.println("Realitza l'exercici anterior utilitzant transaccions.");
        connection.setAutoCommit(false);
        debtUpdatePS = connection.prepareStatement("UPDATE dept SET DNAME = ? WHERE DEPTNO = ?");

        // Pedir al usuario un departament
        System.out.println("Introdueix el departament que vols modificar");
        deptNo = Integer.parseInt(System.console().readLine());

        // Pedir al usuario un nou nom
        System.out.println("Introdueix el nou nom del departament");
        newName = System.console().readLine();

        debtUpdatePS.setString(1, newName);
        debtUpdatePS.setInt(2, deptNo);
        debtUpdateRows = debtUpdatePS.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
        System.out.println(
            String.format(
                "Filas afectadas: %d",
                debtUpdateRows
            )
        );
        debtUpdatePS.close();
        
        connection.close();

    }

}
