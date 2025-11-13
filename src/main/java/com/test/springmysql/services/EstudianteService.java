package com.test.springmysql.services;

import com.test.springmysql.dtos.EstudianteDTO;
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

    public List<EstudianteDTO> getEstudiantes() {
        return estudianteRepository.findAll()
                .stream()
                .map(e->mapper.map(e, EstudianteDTO.class)).toList();

    }

    public EstudianteDTO getEstudiante(Long id) {
        Estudiante e = estudianteRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontrado("estudiante","id",id));
        return mapper.map(e, EstudianteDTO.class);
    }

    public EstudianteDTO createEstudiante(EstudianteDTO dto) {
        Estudiante e = mapper.map(dto, Estudiante.class);
        Estudiante saved = estudianteRepository.save(e);
        return mapper.map(saved, EstudianteDTO.class);
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.findById(id)
                        .orElseThrow(()-> new RecursoNoEncontrado("estudiante","id",id));
        estudianteRepository.deleteById(id);
    }

    public EstudianteDTO updateEstudiante(Long id, EstudianteDTO dto) {
        Estudiante e = estudianteRepository.findById(id)
                        .orElseThrow(()->new RecursoNoEncontrado("estudiante","id",id));
        e.setNombre(dto.getNombre());
        e.setCuil(dto.getCuil());
        Estudiante saved = estudianteRepository.save(e);
        return mapper.map(saved, EstudianteDTO.class);
    }
}
