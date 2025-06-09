package biblioteca.salas.duoc.biblioteca.salas.duoc.controller;

import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.ICarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CarreraController {

    @Autowired
    private ICarreraService carreraService;

    @GetMapping("/carrera")
    public ResponseEntity<?> listadoCarreras() {
        return ResponseEntity.status(200).body(carreraService.findAll());
    }

    @GetMapping("/carrera/{codigo}")
    public ResponseEntity<?> buscarCarreraPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.status(200).body(carreraService.findByCodigo(codigo));
    }
}
