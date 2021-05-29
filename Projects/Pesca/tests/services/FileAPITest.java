package services;

import common.data.Line;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class FileAPITest {

    @Test
    public void searchTest(){

        PescaAPI api = null;
        try {
            api = new PescaAPI();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {
            Assert.assertTrue(
                    api.searchDataInFlow(
                        api.read(api.parseKey("flow","users.txt"))
                        ,'#'
                        ,1
                        ,0
                        ,"carlos")
            );
        } catch (Exception ioException) {
            Assert.fail(ioException.getMessage());
        }

        try {
            Assert.assertTrue(
                    api.searchDataInFlow(
                            api.read(api.parseKey("flow","registers.txt"))
                            ,'#'
                            ,4
                            ,2
                            ,2.554789f)
            );
        } catch (Exception ioException) {
            Assert.fail(ioException.getMessage());
        }

        try {

            int line = api.getLinePositionInFlow(
                    api.read(getClass().getResource("/data/florida.txt").getFile())
                    ,'#'
                    ,4
                    ,0
                    ,"Pargo de cola amarilla"
            );

            Assert.assertEquals(7,line);
        } catch (Exception ioException) {
            Assert.fail(ioException.getMessage());
        }

    }

    @Test
    public void conditionTest(){

        PescaAPI api = null;
        try {
            api = new PescaAPI();
        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

        try {

            int line = api.getLineWhereCondition(
                    api.read(getClass().getResource("/data/florida.txt").getFile())
                    ,'#'
                    ,4
                    ,1
                    ,0.5f
                    ,DataOperation.HIGHER_OR_EQUAL
            );

            Assert.assertEquals(7,line);

            Line l = api.getLineDataInFlow(
                    api.read(getClass().getResource("/data/florida.txt").getFile())
                    ,'#'
                    ,4
                    ,line
            );

            Assert.assertNotNull(l);

            Assert.assertEquals("Pargo de cola amarilla",l.getData()[0].getStringValue());

        } catch (IOException e){
            Assert.fail(e.getMessage());
        }

    }

}
