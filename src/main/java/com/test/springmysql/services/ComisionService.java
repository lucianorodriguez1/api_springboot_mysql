package com.test.springmysql.services;

import com.test.springmysql.dtos.ComisionDTO;
import com.test.springmysql.entities.Comision;
import com.test.springmysql.repositories.ComisionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<ComisionDTO> getComision(Long id){
        Optional<Comision> cOpt = comisionRepository.findById(id);
        return cOpt.map(c->mapper.map(c, ComisionDTO.class));
    }

    public ComisionDTO createComision(Comision comision){
        Comision c =  comisionRepository.save(comision);
        return mapper.map(c, ComisionDTO.class);
    }
    public void deleteComision(Long id){
        comisionRepository.deleteById(id);
    }

    public ComisionDTO updateComision(Long id, Comision comision){
        Optional<Comision> cOpt = comisionRepository.findById(id);
        Comision c = cOpt.get();

        c.setAlumnos_permitidos(comision.getAlumnos_permitidos());
        c.setFecha_final(comision.getFecha_final());
        c.setFecha_inicio(comision.getFecha_inicio());
        c.setMateria(comision.getMateria());

        comisionRepository.save(c);

        return mapper.map(c, ComisionDTO.class);
    }

}
