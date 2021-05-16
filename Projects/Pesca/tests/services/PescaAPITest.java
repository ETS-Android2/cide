package services;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PescaAPITest {

    @Test
    public void userTest(){

        PescaAPI api = null;

        try {
            api = new PescaAPI();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {
            api.registerUser("carlos");
        } catch (IOException ioException) {
            Assert.fail(ioException.getMessage());
        }

        try {
            Assert.assertTrue(api.getUserByIdentifier("carlos"));
        } catch (IOException ioException) {
            Assert.fail(ioException.getMessage());
        }

        try {
            api.closeFlows();
        } catch (IOException ioException) {
            Assert.fail(ioException.getMessage());
        }

    }

}
