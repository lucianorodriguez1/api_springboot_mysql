package com.test.springmysql.services;

import com.test.springmysql.dtos.comisiones.ComisionResumenDTO;
import com.test.springmysql.dtos.estudiantes.EstudianteListDTO;
import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.exceptions.RecursoNoEncontrado;
import com.test.springmysql.repositories.EstudianteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private ModelMapper mapper = new ModelMapper();

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<EstudianteListDTO> getEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(estudiante -> {
                    EstudianteListDTO e = new EstudianteListDTO();
                    e.setId(estudiante.getId());
                    e.setNombre(estudiante.getNombre());
                    e.setCuil(estudiante.getCuil());
                    e.setComisiones(estudiante.getComisiones().stream().map(comision -> {
                        ComisionResumenDTO ec = new ComisionResumenDTO();
                        ec.setId(comision.getId());
                        ec.setNombre_materia(comision.getMateria().getNombre());
                        return ec;
                    }).toList());

                    return e;
                }).toList();
    }

    public EstudianteListDTO getEstudiante(Long id) {
        Estudiante e = estudianteRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontrado("estudiante","id",id));
        EstudianteListDTO el =  mapper.map(e, EstudianteListDTO.class);
        el.setComisiones(e.getComisiones().stream().map(comision -> {
            ComisionResumenDTO ec = new ComisionResumenDTO();
            ec.setId(comision.getId());
            ec.setNombre_materia(comision.getMateria().getNombre());
            return ec;
        }).toList());
        return el;
    }

    public EstudianteListDTO createEstudiante(EstudianteListDTO dto) {
        Estudiante e = mapper.map(dto, Estudiante.class);
        Estudiante saved = estudianteRepository.save(e);
        return mapper.map(saved, EstudianteListDTO.class);
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.findById(id)
                        .orElseThrow(()-> new RecursoNoEncontrado("estudiante","id",id));
        estudianteRepository.deleteById(id);
    }

    public EstudianteListDTO updateEstudiante(Long id, EstudianteListDTO dto) {
        Estudiante e = estudianteRepository.findById(id)
                        .orElseThrow(()->new RecursoNoEncontrado("estudiante","id",id));
        e.setNombre(dto.getNombre());
        e.setCuil(dto.getCuil());
        Estudiante saved = estudianteRepository.save(e);
        return mapper.map(saved, EstudianteListDTO.class);
    }
}
