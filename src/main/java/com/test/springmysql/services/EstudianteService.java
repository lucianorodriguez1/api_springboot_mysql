package com.test.springmysql.services;

import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.repositories.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }
    public List<Estudiante> getEstudiantes(){
        return estudianteRepository.findAll();
    }
    public Optional<Estudiante> getEstudiante(Long id){
        return estudianteRepository.findById(id);
    }
    public void saveOrUpdate(Estudiante estudiante){
        estudianteRepository.save(estudiante);
    }
    public void deleteEstudiante(Long id){
        estudianteRepository.deleteById(id);
    }
}
