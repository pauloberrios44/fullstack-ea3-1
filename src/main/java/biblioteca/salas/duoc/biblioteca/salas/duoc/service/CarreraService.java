package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService implements ICarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<Carrera> findAll() {
        return carreraRepository.findAll();
    }

    @Override
    public Carrera findByCodigo(String codigo) {
        return carreraRepository.findByCodigo(codigo);
    }
}
