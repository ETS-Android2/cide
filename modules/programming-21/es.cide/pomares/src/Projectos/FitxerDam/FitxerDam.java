package Projectos.FitxerDam;

/*

    Project     Programming21
    Package     Projectos.FitxerDam    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-01-19

    DESCRIPTION
    
*/

import java.io.*;

/**
 * @author Carlos Pomares
 */


public class FitxerDam {

    private String pathToFile;
    private String textToWrite;
    private BufferedReader reader;
    private BufferedWriter writer;

    public void fitxer(String path,String mode){
        try {
            this.pathToFile = path;
            if(mode.equals("write")){
                this.writer = new BufferedWriter(new FileWriter(this.pathToFile));
            } else if(mode.equals("read")){
                this.reader = new BufferedReader(new FileReader(this.pathToFile));
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String readFile(int lines){
        String output = "";
        try {
            for (int i = 0; i < lines; i++) {
                output += this.reader.readLine();
            }
            this.reader.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return output;
    }

    public boolean writeFile(String textToWrite){
        try {
            this.writer.write(textToWrite);
            this.writer.close();
            return true;
        } catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
