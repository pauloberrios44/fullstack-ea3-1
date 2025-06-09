package biblioteca.salas.duoc.biblioteca.salas.duoc.service;

import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Carrera;

import java.util.List;

public interface ICarreraService {
    List<Carrera> findAll();

    Carrera findByCodigo(String codigo);
}
