
import java.io.File;

public interface Writer {
    /**
     * 
     * Dado unos alumnos y un archivo, creará el archivo XML con los datos de los alumnos.
     * 
     * @param alumnos Los alumnos a escribir.
     * @param file El archivo donde escribir.
     * @throws Exception Si el archivo no puede ser creado o no se puede escribir en el.
     */
    public void write(Alumno[] alumnos, File file) throws Exception;
}
