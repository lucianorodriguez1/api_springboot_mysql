package com.test.springmysql.services;

import com.test.springmysql.dtos.ComisionDTO;
import com.test.springmysql.entities.Comision;
import com.test.springmysql.exceptions.RecursoNoEncontrado;
import com.test.springmysql.repositories.ComisionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComisionService {
    private final ComisionRepository comisionRepository;
    ModelMapper mapper = new ModelMapper();

    public ComisionService(ComisionRepository comisionRepository) {
        this.comisionRepository = comisionRepository;
    }

    public List<ComisionDTO> getComisiones(){
        return comisionRepository.findAll().stream()
                .map(c -> mapper.map(c, ComisionDTO.class)).toList();
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

}
