package com.test.springmysql.services;

import com.test.springmysql.dtos.MateriaDTO;
import com.test.springmysql.entities.Materia;
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

    public Optional<MateriaDTO> getMateria(Long id) {
        Optional<Materia> m = materiaRepository.findById(id);
        return m.map(materia -> mapper.map(materia, MateriaDTO.class));
    }

    public MateriaDTO saveOrUpdate(Materia materia) {
        Materia m = materiaRepository.save(materia);
        return mapper.map(m, MateriaDTO.class);
    }

    public void deleteMateria(Long id) {
        materiaRepository.deleteById(id);
    }

}
