package services;

import common.data.Data;
import common.specification.Fish;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PescaAPITest {

    @Test
    public void fileTest(){
        PescaAPI api = null;
        try {
            api = new PescaAPI();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }
    }

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
            api.registerUser("adan");
        } catch (Exception ioException) {
            Assert.fail(ioException.getMessage());
        }

        try {
            Assert.assertTrue(api.getUserByIdentifier("adan"));
        } catch (IOException ioException) {
            Assert.fail(ioException.getMessage());
        }

    }

    @Test
    public void deleteUser(){

        PescaAPI api = null;

        try {
            api = new PescaAPI();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {
            api.registerUser("DELETETEST");
        } catch (Exception ioException) {
            Assert.fail(ioException.getMessage());
        }

        try {
            Assert.assertTrue(api.getUserByIdentifier("DELETETEST"));
        } catch (IOException ioException) {
            Assert.fail(ioException.getMessage());
        }

        try {
            api.deleteUser("DELETETEST");
        } catch (Exception ioException){
            Assert.fail(ioException.getMessage());
        }

        try {
            Assert.assertFalse(api.getUserByIdentifier("DELETETEST"));
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void fishTest(){

        PescaAPI api = null;

        try {
            api = new PescaAPI();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {
            api.registerNewAction("carlos","Cap Roig",0.45f);
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }

    }

}
