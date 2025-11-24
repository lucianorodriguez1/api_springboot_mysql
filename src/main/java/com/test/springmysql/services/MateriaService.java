package com.test.springmysql.services;

import com.test.springmysql.dtos.ComisionDTO;
import com.test.springmysql.dtos.MateriaDetailDTO;
import com.test.springmysql.dtos.MateriaListDTO;
import com.test.springmysql.entities.Comision;
import com.test.springmysql.entities.Materia;
import com.test.springmysql.exceptions.RecursoNoEncontrado;
import com.test.springmysql.repositories.ComisionRepository;
import com.test.springmysql.repositories.MateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    public List<MateriaListDTO> getMaterias() {
        return materiaRepository.findAll().stream().map(m -> {

            MateriaListDTO dto = mapper.map(m, MateriaListDTO.class);

            dto.setComisionesId(
                    m.getComisiones().stream().map(c->
                            c.getId())
                            .toList()
            );
            return dto;
        }).toList();

    }


    public MateriaDetailDTO getMateria(Long id) {
        Materia m = materiaRepository.findById(id)
                .orElseThrow(()->new RecursoNoEncontrado("materia","id",id));
        MateriaDetailDTO dto =  mapper.map(m, MateriaDetailDTO.class);

        dto.setComisiones(m.getComisiones().stream()
                .map(comision -> {
                    ComisionDTO coDto =  mapper.map(comision, ComisionDTO.class);
                    coDto.setEstudiantesId(comision.getEstudiantes().stream()
                            .map(e->e.getId())
                            .toList()
                    );
                    return coDto;
                })
                .toList()
        );

        return dto;
    }

    public MateriaListDTO createMateria(MateriaListDTO materiadto) {
        Materia materia = mapper.map(materiadto, Materia.class);
        Materia saved = materiaRepository.save(materia);
        return mapper.map(saved, MateriaListDTO.class);
    }

    public void deleteMateria(Long id) {
        materiaRepository.findById(id)
                        .orElseThrow(()->new RecursoNoEncontrado("materia","id",id));
        materiaRepository.deleteById(id);
    }

    public MateriaListDTO updateMateria(Long id, MateriaListDTO dto){
       Materia m = materiaRepository.findById(id)
               .orElseThrow(()->new RecursoNoEncontrado("materia","id",id));

        m.setNombre(dto.getNombre());
        Materia saved = materiaRepository.save(m);
        return mapper.map(saved, MateriaListDTO.class);
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
