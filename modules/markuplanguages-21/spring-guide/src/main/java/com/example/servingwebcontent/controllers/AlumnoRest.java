package com.example.servingwebcontent.controllers;

import com.example.servingwebcontent.representation.Alumno;
import com.example.servingwebcontent.representation.AlumnoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlumnoRest {

    @Autowired
    private AlumnoRepository repository;

    @GetMapping(value = "/alumno",produces = "application/json")
    public String getAlumno(@RequestParam(name = "id",required = true,defaultValue = "0") int id) throws JsonProcessingException {
        Alumno alumno = repository.findById(id);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(alumno);
    }

}
