package com.test.springmysql.services;

import com.test.springmysql.dtos.ProfesorDTO;
import com.test.springmysql.entities.Profesor;
import com.test.springmysql.exceptions.RecursoNoEncontrado;
import com.test.springmysql.repositories.ProfesorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {
    private final ProfesorRepository profesorRepository;
    private final ModelMapper mapper = new ModelMapper();


    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public List<ProfesorDTO> getProfesores(){
        return profesorRepository.findAll().stream().map(p->mapper.map(p, ProfesorDTO.class)).toList();
    }
    public ProfesorDTO getProfesor(Long id){
        Profesor p = profesorRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontrado("profesor", "id", id));
        return mapper.map(p, ProfesorDTO.class);
    }
    public ProfesorDTO createProfesor(ProfesorDTO profesordto){
        Profesor p = mapper.map(profesordto,Profesor.class);
        Profesor saved =  profesorRepository.save(p);
        return mapper.map(saved, ProfesorDTO.class);
    }
    public void deleteProfesor(Long id){
        profesorRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontrado("profesor", "id", id));
        profesorRepository.deleteById(id);
    }
    public ProfesorDTO updateProfesor(Long id, ProfesorDTO profesordto){
        Profesor p = profesorRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontrado("profesor", "id", id));

        p.setNombre(profesordto.getNombre());
        p.setAntiguedad_universidad(profesordto.getAntiguedad_universidad());

        Profesor saved = profesorRepository.save(p);
        return mapper.map(saved, ProfesorDTO.class);
    }

}
