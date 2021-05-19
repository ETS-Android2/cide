package services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PescaAPITest {

    @Test
    public void fileTest(){
        PescaAPI api = null;
        try {
            api = new PescaAPI();
        } catch (IOException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void userTest(){

        PescaAPI api = null;

        try {
            api = new PescaAPI();
        } catch (IOException e){
            Assertions.fail(e.getMessage());
        }

        try {
            api.registerUser("carlos");
            api.registerUser("adan");
        } catch (IOException ioException) {
            Assertions.fail(ioException.getMessage());
        }

        try {
            Assertions.assertTrue(api.getUserByIdentifier("adan"));
        } catch (IOException ioException) {
            Assertions.fail(ioException.getMessage());
        }

    }

}
