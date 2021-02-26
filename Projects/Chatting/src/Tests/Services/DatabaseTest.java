package Services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


/*

    Project     Programming21
    Package     Services    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-02-25

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */
    class DatabaseTest {

    @Test
    void newQuery() {

        Database db;
        ResultSet rs;

        String SQL = "SELECT message_id FROM message_transaction A INNER JOIN ( SELECT A.id AS user_id,A.username AS user_name,B.id AS message_id,content AS message_content FROM user A INNER JOIN message B ON A.id = B.user WHERE A.id = 1 ) B ON A.message = B.message_id WHERE A.room = 1 ORDER BY A.creation_date DESC LIMIT 1;";

        /*try {
            db = Database.init();
            rs = Database.newQuery(db,SQL);
            assertNotNull(rs);
        } catch (Exception e){
            //
        }*/



    }

    @Disabled
    @Test
    void newUpdate() {
    }

}