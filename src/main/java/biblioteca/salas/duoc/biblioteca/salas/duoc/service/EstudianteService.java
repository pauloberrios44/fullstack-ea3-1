package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante getEstudianteById(Integer id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    public Estudiante getEstudianteByRun(String run) {
        Estudiante estudiante = estudianteRepository.findByRun(run);
        if (estudiante == null) {
            return null;
        }

        return estudiante;
    }
}
