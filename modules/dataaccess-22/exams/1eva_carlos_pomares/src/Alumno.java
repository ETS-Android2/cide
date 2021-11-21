/** 
 * @author Carlos Pomares (https://www.github.com/pomaretta)
 */

public class Alumno {
    
    // El Identificador del alumno
    private Integer id;
    // El nombre del alumno
    private String name;
    // El año de nacimiento del alumno
    private String year;
    
    // El curso del alumno
    private String grade;
    // El colegio del alumno
    private String school;

    public Alumno() {}

    /**
     * 
     * Representa a un alumno
     * 
     * @param id El identificador del alumno
     * @param name El nombre del alumno
     * @param year El año de nacimiento del alumno
     * @param grade El curso del alumno
     * @param school El colegio del alumno
     */
    private Alumno(Integer id, String name, String year, String grade, String school) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.grade = grade;
        this.school = school;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getGrade() {
        return grade;
    }

    public String getSchool() {
        return school;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", grade='" + grade + '\'' +
                ", school='" + school + '\'' +
                '}';
    }

    /**
     * 
     * Permite crear un objeto alumno dados sus datos
     * 
     * @param id El identificador del alumno
     * @param name El nombre del alumno
     * @param year El año de nacimiento del alumno
     * @param grade El curso del alumno
     * @param school El colegio del alumno
     * @return Un objeto alumno
     */
    public static Alumno createAlumno(int id, String name, String year, String grade, String school) {
        return new Alumno(
            id,
            name,
            year,
            grade,
            school
        );
    }

}
