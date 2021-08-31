package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.representation.Alumno;
import com.example.servingwebcontent.representation.AlumnoRepository;
import com.example.servingwebcontent.representation.Asignatura;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlumnoView {

    @Autowired
    private AlumnoRepository repository;

    @GetMapping("/processJson")
    public String alumno(){
        return "alumno";
    }

    @RequestMapping(value = "/processJson",method = RequestMethod.POST)
    public String processAlumno(String content, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Alumno alumno = mapper.readValue(content,Alumno.class);
        repository.save(alumno);
        model.addAttribute("email",alumno.getId());
        model.addAttribute("password",alumno.getEdad());
        return "welcome";
    }

}
