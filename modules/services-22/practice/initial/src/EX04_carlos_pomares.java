/**
 * 
 * Servicios y Procesos - 21/22
 * 
 * Práctica 4
 * 
 * Crear un programa que sea capaz de contar cuantas vocales hay en un fichero 
 * (por ejemplo el texto de este enunciado). El programa padre debe lanzar 
 * cinco procesos hijo, donde cada uno de ellos se ocupará de contar 
 * una vocal concreta (que puede ser minúscula o mayúscula). 
 * 
 * Cada subproceso que cuenta vocales deberá dejar el resultado en un fichero. 
 * El programa padre se ocupará de recuperar los resultados de los ficheros, 
 * sumar todos los subtotales y mostrar el resultado final en pantalla.
 * 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 * 
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class EX04_carlos_pomares {
    
    private static HashMap<String,Integer> counter = new HashMap<String,Integer>();

    public Thread readLineWithLetter(final Character letter,final String text) {
        return new Thread(){
            @Override
            public void run() {
                super.run();
                for (Character c : text.toCharArray()) {
                    if (c != letter) continue;
                    if (!counter.containsKey(Character.toString(letter))) {
                        counter.put(Character.toString(letter),1);    
                        continue;
                    }
                    counter.replace(Character.toString(letter), counter.get(Character.toString(letter)) + 1);
                }
            }
        };
    }

    public int readLetterFromFile(Character letter, File origin, File destination) throws Exception {

        ProcessBuilder pb = new ProcessBuilder(new String[]{"/bin/cat", origin.getAbsolutePath()});
        Process p = pb.start();

        InputStreamReader r = new InputStreamReader(p.getInputStream());

        int c = 0;
        int counter = 0;
        while ((c = r.read()) != -1) {
            if (Character.toLowerCase((char) c) == letter) counter++;    
        }

        r.close();

        FileWriter writer = new FileWriter(destination);
        writer.write(String.valueOf(counter));
        writer.close();

        return p.waitFor();
    }

    private int readNumberOfFile(File f) throws IOException {
        FileReader reader = new FileReader(f);
        int n = (char) reader.read();
        reader.close();
        return n;
    }

    public int sumAllFiles(ArrayList<File> files) throws IOException {
        int counter = 0;
        for (File file : files) {
            counter += Integer.valueOf(
                Character.toString(
                    (char) this.readNumberOfFile(file)
                )
            );
        }
        return counter;
    }

    public static void main(String[] args) throws Exception {
        
        ArrayList<Integer> threads = new ArrayList<Integer>();
        ArrayList<File> files = new ArrayList<File>();
        EX04_carlos_pomares app = new EX04_carlos_pomares();

        String data = "Hello World!";

        Character[] letters = new Character[]{
            'a', 'e', 'i', 'o', 'u'
        };

        for (Character letter : letters) {
            File file = new File(String.format("./%s.txt", Character.toString(letter)));
            files.add(file);
            threads.add(
                app.readLetterFromFile(
                    letter
                    ,new File("./origin.txt")
                    ,file
                )
            );
        }

        int sum = app.sumAllFiles(files);
        System.out.println(sum);

    }

}
