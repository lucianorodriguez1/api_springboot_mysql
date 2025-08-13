package com.test.springmysql.services;

import com.test.springmysql.entities.Comision;
import com.test.springmysql.repositories.ComisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComisionService {
    private final ComisionRepository comisionRepository;

    public ComisionService(ComisionRepository comisionRepository) {
        this.comisionRepository = comisionRepository;
    }
    public List<Comision> getComisiones(){
        return comisionRepository.findAll();
    }
    public Optional<Comision> getComision(Long id){
        return comisionRepository.findById(id);
    }
    public Comision saveOrUpdate(Comision comision){
        return comisionRepository.save(comision);
    }
    public void deleteComision(Long id){
        comisionRepository.deleteById(id);
    }
}
