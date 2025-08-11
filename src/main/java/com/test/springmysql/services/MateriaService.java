package com.test.springmysql.services;

import com.test.springmysql.entities.Materia;
import com.test.springmysql.repositories.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    //Autowired nos ayuda con la inyeccion de . En este caso le decimos que inyecte en
    // materiaRepository lo que hay en MateriaRepository. No es necesario si se inyecta por constructor
    //@Autowired
    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public List<Materia> getMaterias(){
        return materiaRepository.findAll();
    }

    public Optional<Materia> getMateria(Long id){
        return materiaRepository.findById(id);
    }

    public void saveOrUpdate(Materia materia){
         materiaRepository.save(materia);
    }
    public void deleteMateria(Long id){
         materiaRepository.deleteById(id);
    }

}
