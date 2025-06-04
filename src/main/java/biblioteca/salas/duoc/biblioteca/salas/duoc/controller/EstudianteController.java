package biblioteca.salas.duoc.biblioteca.salas.duoc.controller;

import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<?> getEstudiante(@PathVariable int id) {
        Estudiante estudiante = estudianteService.getEstudianteById(id);
        if (estudiante == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(estudiante);
    }

    @GetMapping("/estudiante/{run}/buscar-run")
    public ResponseEntity<?> getEstudianteBuscarRun(@PathVariable String run) {
        Estudiante estudiante = estudianteService.getEstudianteByRun(run);
        if (estudiante == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(estudiante);
    }
}
