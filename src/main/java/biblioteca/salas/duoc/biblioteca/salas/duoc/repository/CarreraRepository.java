package biblioteca.salas.duoc.biblioteca.salas.duoc.repository;

import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, String> {

    Carrera findByCodigo(String codigo);
}
