package com.test.springmysql.services;

import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.entities.Profesor;
import com.test.springmysql.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {
    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<Profesor> getProfesores(){
        return profesorRepository.findAll();
    }
    public Optional<Profesor> getProfesor(Long id){
        return profesorRepository.findById(id);
    }
    public Profesor saveOrUpdate(Profesor profesor){
        return profesorRepository.save(profesor);
    }
    public void deleteProfesor(Long id){
        profesorRepository.deleteById(id);
    }


}
