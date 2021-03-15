package Application.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/*

    Project     Programming21
    Package     Application.Entities    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-15

    DESCRIPTION
    
*/

/**
 * @author Carlos Pomares
 */

class ClientTest {

    @Test
    void getId() {

        Client c = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertEquals(1,c.getId());

    }

    @Test
    void getFirstName() {

        Client c = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertEquals("Paco",c.getFirstName());

        Client c1 = new Client(1, null
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertNull(c1.getFirstName());

    }

    @Test
    void getSecondName() {

        Client c = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertEquals("Manuel",c.getSecondName());

        Client c1 = new Client(1, "Paco"
                ,null
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertNull(c1.getSecondName());

    }

    @Test
    void getFirstLastname() {

        Client c = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertEquals("Martinez",c.getFirstLastname());

        Client c1 = new Client(1, "Paco"
                ,"Manuel"
                ,null
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertNull(c1.getFirstLastname());

    }

    @Test
    void getSecondLastname() {

        Client c = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertEquals("Jimenez",c.getSecondLastname());

        Client c1 = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,null
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertNull(c1.getSecondLastname());

    }

    @Test
    void getStreetAddress() {

        Client c = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertEquals("San Valentin",c.getStreetAddress());

        Client c1 = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,null
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertNull(c1.getStreetAddress());

    }

    @Test
    void getMailAddress() {

        Client c = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertEquals("paco@gmail.com",c.getMailAddress());

        Client c1 = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,null
                ,"699888555"
        );

        assertNull(c1.getMailAddress());

    }

    @Test
    void getPhoneNumber() {

        Client c = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,"699888555"
        );

        assertEquals("699888555",c.getPhoneNumber());

        Client c1 = new Client(1, "Paco"
                ,"Manuel"
                ,"Martinez"
                ,"Jimenez"
                ,"San Valentin"
                ,"paco@gmail.com"
                ,null
        );

        assertNull(c1.getPhoneNumber());

    }
}