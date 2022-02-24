package com.github.pomaretta.cide.dto;

import com.github.pomaretta.cide.entity.Person;

public class PersonTeacher {
    
    private Person person;
    private boolean teacher;

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setTeacher(boolean teacher) {
        this.teacher = teacher;
    }

    public Person getPerson() {
        return person;
    }

    public boolean isTeacher() {
        return teacher;
    }

}
