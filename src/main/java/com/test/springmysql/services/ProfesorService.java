package com.test.springmysql.services;

import com.test.springmysql.dtos.ProfesorDTO;
import com.test.springmysql.entities.Profesor;
import com.test.springmysql.repositories.ProfesorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<ProfesorDTO> getProfesor(Long id){
        return profesorRepository.findById(id).map(profesor -> mapper.map(profesor, ProfesorDTO.class));
    }
    public ProfesorDTO createProfesor(Profesor profesor){
        Profesor p =  profesorRepository.save(profesor);
        return mapper.map(p, ProfesorDTO.class);
    }
    public void deleteProfesor(Long id){
        profesorRepository.deleteById(id);
    }
    public ProfesorDTO updateProfesor(Long id, Profesor profesor){
        Optional<Profesor> pOpt = profesorRepository.findById(id);
        if(pOpt.isEmpty()) {
            return null;
        }
        Profesor p = pOpt.get();
        p.setNombre(profesor.getNombre());
        p.setAntiguedad_universidad(profesor.getAntiguedad_universidad());
        profesorRepository.save(p);
        return mapper.map(p, ProfesorDTO.class);
    }

}
