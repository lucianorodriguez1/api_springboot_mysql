package com.test.springmysql.services;

import com.test.springmysql.dtos.comisiones.ComisionDetailDTO;
import com.test.springmysql.dtos.comisiones.ComisionEstudianteDTO;
import com.test.springmysql.dtos.comisiones.ComisionListDTO;
import com.test.springmysql.dtos.comisiones.ComisionProfesorDTO;
import com.test.springmysql.entities.Comision;
import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.entities.Profesor;
import com.test.springmysql.exceptions.EstudianteYaExisteEnComision;
import com.test.springmysql.exceptions.RecursoNoEncontrado;
import com.test.springmysql.repositories.ComisionRepository;
import com.test.springmysql.repositories.EstudianteRepository;
import com.test.springmysql.repositories.ProfesorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComisionService {
    private final ComisionRepository comisionRepository;
    private final EstudianteRepository estudianteRepository;
    private final ProfesorRepository profesorRepository;

    ModelMapper mapper = new ModelMapper();

    public ComisionService(ComisionRepository comisionRepository, EstudianteRepository estudianteRepository, ProfesorRepository profesorRepository) {
        this.comisionRepository = comisionRepository;
        this.estudianteRepository = estudianteRepository;
        this.profesorRepository = profesorRepository;
    }

    public List<ComisionListDTO> getComisiones(){
        return comisionRepository.findAll().stream()
                .map(c -> {
                    ComisionListDTO dto = new ComisionListDTO();
                    dto.setId(c.getId());
                    dto.setAlumnosPermitidos(c.getAlumnosPermitidos());
                    dto.setFechaInicio(c.getFechaInicio());
                    dto.setFechaFinal(c.getFechaFinal());
                    dto.setHoraInicio(c.getHoraInicio());
                    dto.setHoraFinal(c.getHoraFinal());

                    dto.setMateriaId(c.getMateria().getId());
                    dto.setMateriaNombre(c.getMateria().getNombre());
                    dto.setProfesoresId(c.getProfesores().stream()
                            .map(p->p.getId()).toList());
                    dto.setCant_estudiantes(c.getEstudiantes().size());

                    return dto;
                }).toList();
    }
    public ComisionDetailDTO getComision(Long id){
       Comision c = comisionRepository.findById(id)
               .orElseThrow(()-> new RecursoNoEncontrado("comision","id",id));
       ComisionDetailDTO cd =  new ComisionDetailDTO();
       cd.setAlumnosPermitidos(c.getAlumnosPermitidos());
       cd.setFechaInicio(c.getFechaInicio());
       cd.setFechaFinal(c.getFechaFinal());
       cd.setHoraInicio(c.getHoraInicio());
       cd.setHoraFinal(c.getHoraFinal());
       cd.setId(c.getId());
       cd.setMateriaNombre(c.getMateria().getNombre());

       cd.setProfesores(c.getProfesores().stream()
               .map(profesor -> {
                   ComisionProfesorDTO cp = new ComisionProfesorDTO();
                   cp.setNombre(profesor.getNombre());
                   cp.setId(profesor.getId());

                   return cp;
               }).toList());

       cd.setEstudiantes(c.getEstudiantes().stream()
               .map(estudiante -> {
                   ComisionEstudianteDTO ce = new ComisionEstudianteDTO();
                   ce.setId(estudiante.getId());
                   ce.setNombre(estudiante.getNombre());
                   ce.setCuil(estudiante.getCuil());
                   return ce;
               }).toList());

       return cd;
    }

    public ComisionListDTO createComision(ComisionListDTO comisiondto){
        Comision c = mapper.map(comisiondto, Comision.class);
        Comision saved =  comisionRepository.save(c);
        return mapper.map(saved, ComisionListDTO.class);
    }
    public void deleteComision(Long id){
        comisionRepository.findById(id)
                        .orElseThrow(()->new RecursoNoEncontrado("comision","id",id));
        comisionRepository.deleteById(id);
    }

    public ComisionListDTO updateComision(Long id, ComisionListDTO comisiondto){
        Comision c = comisionRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontrado("comision","id",id));

        c.setAlumnosPermitidos(comisiondto.getAlumnosPermitidos());
        c.setFechaFinal(comisiondto.getFechaFinal());
        c.setFechaInicio(comisiondto.getFechaInicio());

        Comision saved = comisionRepository.save(c);
        return mapper.map(saved, ComisionListDTO.class);
    }

    public void addStudent(Long cid, Long eid){
        Comision c = comisionRepository.findById(cid)
                .orElseThrow(()-> new RecursoNoEncontrado("comision","id",cid));
        Estudiante e = estudianteRepository.findById(eid)
                .orElseThrow(()-> new RecursoNoEncontrado("estudiante","id",eid));

        boolean existe = c.getEstudiantes().stream()
                .anyMatch(est -> est.getId()== eid);

        if (existe) {
            throw new EstudianteYaExisteEnComision(e.getId(),c.getId());
        }
        c.getEstudiantes().add(e);
        comisionRepository.save(c);
    }

    public void addProfesor(Long cid,Long pid){
        Comision c = comisionRepository.findById(cid)
                .orElseThrow(()->new RecursoNoEncontrado("comision","cid",cid));

        Profesor p = profesorRepository.findById(pid)
                .orElseThrow(()-> new RecursoNoEncontrado("profesor", "pid", pid));

        c.getProfesores().add(p);
        comisionRepository.save(c);
    }


}
