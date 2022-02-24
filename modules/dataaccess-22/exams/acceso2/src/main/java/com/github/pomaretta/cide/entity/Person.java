package com.github.pomaretta.cide.entity;

import java.sql.Date;

import javax.persistence.Transient;

public class Person implements Comparable<Person>{
    private int id;
    private String nif;
    private String name;
    private String firstLastname;
    private String secondLastname;
    private Date birthdate;
    private String gender;
    private String telephone;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLastname() {
        return firstLastname;
    }

    public void setFirstLastname(String firstLastname) {
        this.firstLastname = firstLastname;
    }

    public String getSecondLastname() {
        return secondLastname;
    }

    public void setSecondLastname(String secondLastname) {
        this.secondLastname = secondLastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (nif != null ? !nif.equals(person.nif) : person.nif != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (firstLastname != null ? !firstLastname.equals(person.firstLastname) : person.firstLastname != null)
            return false;
        if (secondLastname != null ? !secondLastname.equals(person.secondLastname) : person.secondLastname != null)
            return false;
        if (birthdate != null ? !birthdate.equals(person.birthdate) : person.birthdate != null) return false;
        if (gender != null ? !gender.equals(person.gender) : person.gender != null) return false;
        if (telephone != null ? !telephone.equals(person.telephone) : person.telephone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nif != null ? nif.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (firstLastname != null ? firstLastname.hashCode() : 0);
        result = 31 * result + (secondLastname != null ? secondLastname.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person" +
                "{" +
                "id=" + id +
                ", nif='" + nif + '\'' +
                ", name='" + name + '\'' +
                ", firstLastname='" + firstLastname + '\'' +
                ", secondLastname='" + secondLastname + '\'' +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", telephone=" + telephone + 
                "}";
    }

    @Override
    public int compareTo(Person o) {
        return String.format(
            "%s %s %s",
            this.name,
            this.firstLastname,
            this.secondLastname
        ).compareTo(
            String.format(
                "%s %s %s",
                o.name,
                o.firstLastname,
                o.secondLastname
            )
        );
    }

}
