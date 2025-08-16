package com.test.springmysql.services;

import com.test.springmysql.dtos.EstudianteDTO;
import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.repositories.EstudianteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<EstudianteDTO> getEstudiante(Long id) {
        Optional<Estudiante> eOpt=  estudianteRepository.findById(id);
        if(eOpt.isEmpty()){
            return null;
        }
        return eOpt.map(e->mapper.map(e,EstudianteDTO.class));
    }

    public EstudianteDTO createEstudiante(Estudiante estudiante) {
        Estudiante e = estudianteRepository.save(estudiante);
        return mapper.map(e, EstudianteDTO.class);
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    public EstudianteDTO updateEstudiante(Long id, Estudiante estudiante) {
        Optional<Estudiante> eOpt = estudianteRepository.findById(id);
        if(eOpt.isEmpty()){
            return null;
        }
        Estudiante e = eOpt.get();
        e.setNombre(estudiante.getNombre());
        e.setCuil(estudiante.getCuil());
        e.setComisiones(estudiante.getComisiones());
        estudianteRepository.save(e);
        return mapper.map(e, EstudianteDTO.class);

    }
}
