package com.test.springmysql.services;

import com.test.springmysql.dtos.ComisionDTO;
import com.test.springmysql.dtos.MateriaDTO;
import com.test.springmysql.entities.Comision;
import com.test.springmysql.entities.Materia;
import com.test.springmysql.exceptions.RecursoNoEncontrado;
import com.test.springmysql.repositories.ComisionRepository;
import com.test.springmysql.repositories.MateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;
    private final ComisionRepository comisionRepository;

    private final ModelMapper mapper = new ModelMapper();

    public MateriaService(MateriaRepository materiaRepository, ComisionRepository comisionRepository) {
        this.materiaRepository = materiaRepository;
        this.comisionRepository = comisionRepository;
    }

    public List<MateriaDTO> getMaterias() {
        return materiaRepository.findAll().stream().map(m -> {

            MateriaDTO dto = mapper.map(m, MateriaDTO.class);

            dto.setComisionesId(
                    m.getComisiones().stream().map(c->
                            c.getId())
                            .toList()
            );
            return dto;
        }).toList();

    }


    public MateriaDTO getMateria(Long id) {
        Materia m = materiaRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontrado("materia","id",id));
        return mapper.map(m, MateriaDTO.class);
    }

    public MateriaDTO createMateria(MateriaDTO materiadto) {
        Materia materia = mapper.map(materiadto, Materia.class);
        Materia saved = materiaRepository.save(materia);
        return mapper.map(saved, MateriaDTO.class);
    }

    public void deleteMateria(Long id) {
        materiaRepository.findById(id)
                        .orElseThrow(()->new RecursoNoEncontrado("materia","id",id));
        materiaRepository.deleteById(id);
    }

    public MateriaDTO updateMateria(Long id, MateriaDTO dto){
       Materia m = materiaRepository.findById(id)
               .orElseThrow(()->new RecursoNoEncontrado("materia","id",id));

        m.setNombre(dto.getNombre());
        Materia saved = materiaRepository.save(m);
        return mapper.map(saved, MateriaDTO.class);
    }

    public ComisionDTO createComision(Long id,ComisionDTO comision){
        Materia m = materiaRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontrado("materia","id",id));
        Comision c = mapper.map(comision, Comision.class);
        c.setMateria(m);
        Comision saved = comisionRepository.save(c);
        return mapper.map(saved, ComisionDTO.class);
    }


}
