package services;

import common.data.Data;
import common.specification.Boat;
import common.specification.Fish;
import common.specification.StatisticResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
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

        PescaAPISimple api = null;

        try {
            api = new PescaAPISimple();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {
            api.registerUser("carlos");
        } catch (Exception ioException) {
            System.out.println(ioException.getMessage());
            Assert.fail(ioException.getMessage());
        }

        try {
            Assert.assertTrue(api.getUserByIdentifier("carlos"));
        } catch (IOException ioException) {
            Assert.fail(ioException.getMessage());
        }

    }

    @Test
    public void deleteUser(){

        PescaAPISimple api = null;

        try {
            api = new PescaAPISimple();
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
    public void actionTest(){

        PescaAPISimple api = null;

        try {
            api = new PescaAPISimple();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {
            api.registerNewAction("carlos",api.getFish(getClass().getResource("/data/florida.txt").getFile()));
        } catch (Exception e){
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void fishTest(){

        PescaAPISimple api = null;

        try {
            api = new PescaAPISimple();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {
            Fish f = api.getFish(getClass().getResource("/data/florida.txt").getFile());
            Assert.assertNotEquals("", f.getName());
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void statisticsTest(){

        PescaAPISimple api = null;

        try {
            api = new PescaAPISimple();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {
            StatisticResult result = api.getStatistics("carlos");
        } catch (Exception e){
            System.out.println(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void boatTest(){

        PescaAPI api = null;

        try {
            api = new PescaAPI();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {
            api.deleteUserInBoat(api.getBoat("elpatron"), api.getUser("carlos"));
            Assert.assertTrue(api.getBoatByIdentifier("elpatron"));
        } catch (Exception e){
            System.out.println(e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

}
