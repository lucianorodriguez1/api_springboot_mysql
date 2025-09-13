package com.test.springmysql.services;

import com.test.springmysql.dtos.MateriaDTO;
import com.test.springmysql.entities.Materia;
import com.test.springmysql.exceptions.RecursoNoEncontrado;
import com.test.springmysql.repositories.MateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    //Autowired nos ayuda con la inyeccion de . En este caso le decimos que inyecte en
    // materiaRepository lo que hay en MateriaRepository. No es necesario si se inyecta por constructor
    //@Autowired
    private final MateriaRepository materiaRepository;
    private final ModelMapper mapper = new ModelMapper();

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public List<MateriaDTO> getMaterias() {
        return materiaRepository.findAll().stream().map(m -> mapper.map(m, MateriaDTO.class)).toList();
    }

    public MateriaDTO getMateria(Long id) {
        Optional<Materia> m = materiaRepository.findById(id);
        if(!m.isPresent()){
            throw new RecursoNoEncontrado("cliente","id",id);
        }
        return mapper.map(m.get(), MateriaDTO.class);
    }

    public MateriaDTO createMateria(Materia materia) {
        Materia m = materiaRepository.save(materia);
        return mapper.map(m, MateriaDTO.class);
    }

    public void deleteMateria(Long id) {
        if(!materiaRepository.existsById(id)) {
            throw new RecursoNoEncontrado("cliente","id",id);
        }
        materiaRepository.deleteById(id);
    }

    public MateriaDTO updateMateria(Long id, Materia materia){
        Optional<Materia> mOpt = materiaRepository.findById(id);
        if(!mOpt.isPresent()){
            throw new RecursoNoEncontrado("cliente","id",id);
        }
        Materia m = mOpt.get();
        m.setNombre(materia.getNombre());
        materiaRepository.save(m);
        return mapper.map(m, MateriaDTO.class);
    }

}
