

public class Alumno {
    
    private Integer id;
    private String name;
    private String year;

    private String grade;
    private String school;

    public Alumno() {}

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
