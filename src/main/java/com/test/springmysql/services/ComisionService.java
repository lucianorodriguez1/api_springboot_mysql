package com.test.springmysql.services;

import com.test.springmysql.dtos.ComisionDTO;
import com.test.springmysql.entities.Comision;
import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.exceptions.RecursoNoEncontrado;
import com.test.springmysql.repositories.ComisionRepository;
import com.test.springmysql.repositories.EstudianteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComisionService {
    private final ComisionRepository comisionRepository;
    private final EstudianteRepository estudianteRepository;

    ModelMapper mapper = new ModelMapper();

    public ComisionService(ComisionRepository comisionRepository, EstudianteRepository estudianteRepository) {
        this.comisionRepository = comisionRepository;
        this.estudianteRepository = estudianteRepository;
    }

    public List<ComisionDTO> getComisiones(){
        return comisionRepository.findAll().stream()
                .map(c -> {
                    ComisionDTO dto = mapper.map(c, ComisionDTO.class);

                    dto.setEstudiantesId(
                            c.getEstudiantes().stream().map(e->e.getId()).toList()
                    );
                    return dto;
                }).toList();
    }
    public ComisionDTO getComision(Long id){
       Comision c = comisionRepository.findById(id)
               .orElseThrow(()-> new RecursoNoEncontrado("comision","id",id));
       return mapper.map(c, ComisionDTO.class);
    }

    public ComisionDTO createComision(ComisionDTO comisiondto){
        Comision c = mapper.map(comisiondto, Comision.class);
        Comision saved =  comisionRepository.save(c);
        return mapper.map(saved, ComisionDTO.class);
    }
    public void deleteComision(Long id){
        comisionRepository.findById(id)
                        .orElseThrow(()->new RecursoNoEncontrado("comision","id",id));
        comisionRepository.deleteById(id);
    }

    public ComisionDTO updateComision(Long id, ComisionDTO comisiondto){
        Comision c = comisionRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontrado("comision","id",id));

        c.setAlumnos_permitidos(comisiondto.getAlumnos_permitidos());
        c.setFecha_final(comisiondto.getFecha_final());
        c.setFecha_inicio(comisiondto.getFecha_inicio());

        Comision saved = comisionRepository.save(c);
        return mapper.map(saved, ComisionDTO.class);
    }

    public void addStudent(Long cid, Long eid){
        Comision c = comisionRepository.findById(cid)
                .orElseThrow(()-> new RecursoNoEncontrado("comision","id",cid));
        Estudiante e = estudianteRepository.findById(eid)
                .orElseThrow(()-> new RecursoNoEncontrado("estudiante","id",eid));

        c.getEstudiantes().add(e);
        comisionRepository.save(c);
    }




}
