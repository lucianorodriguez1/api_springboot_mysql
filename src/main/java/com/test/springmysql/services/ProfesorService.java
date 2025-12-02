package com.test.springmysql.services;

import com.test.springmysql.dtos.comisiones.ComisionResumenDTO;
import com.test.springmysql.dtos.profesores.ProfesorListDTO;
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

    public List<ProfesorListDTO> getProfesores(){
        return profesorRepository.findAll().stream()
                .map(profesor -> {
                    ProfesorListDTO pl = new ProfesorListDTO();
                    pl.setAntiguedad_universidad(profesor.getAntiguedad_universidad());
                    pl.setId(profesor.getId());
                    pl.setNombre(profesor.getNombre());
                    pl.setComisiones(profesor.getComisiones().stream()
                            .map(comision -> {
                                ComisionResumenDTO cr = new ComisionResumenDTO();
                                cr.setId(comision.getId());
                                cr.setNombre_materia(comision.getMateria().getNombre());
                                return cr;
                            }).toList());
                    return pl;
                }).toList();
    }
    public ProfesorListDTO getProfesor(Long id){
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontrado("profesor", "id", id));
        ProfesorListDTO pl = new ProfesorListDTO();
        pl.setAntiguedad_universidad(profesor.getAntiguedad_universidad());
        pl.setId(profesor.getId());
        pl.setNombre(profesor.getNombre());
        pl.setComisiones(profesor.getComisiones().stream()
                .map(comision -> {
                    ComisionResumenDTO cr = new ComisionResumenDTO();
                    cr.setId(comision.getId());
                    cr.setNombre_materia(comision.getMateria().getNombre());
                    return cr;
                }).toList());
        return pl;
    }
    public ProfesorListDTO createProfesor(ProfesorListDTO profesordto){
        Profesor p = mapper.map(profesordto,Profesor.class);
        Profesor saved =  profesorRepository.save(p);
        return mapper.map(saved, ProfesorListDTO.class);
    }
    public void deleteProfesor(Long id){
        profesorRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontrado("profesor", "id", id));
        profesorRepository.deleteById(id);
    }
    public ProfesorListDTO updateProfesor(Long id, ProfesorListDTO profesordto){
        Profesor p = profesorRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontrado("profesor", "id", id));

        p.setNombre(profesordto.getNombre());
        p.setAntiguedad_universidad(profesordto.getAntiguedad_universidad());

        Profesor saved = profesorRepository.save(p);
        return mapper.map(saved, ProfesorListDTO.class);
    }

}
