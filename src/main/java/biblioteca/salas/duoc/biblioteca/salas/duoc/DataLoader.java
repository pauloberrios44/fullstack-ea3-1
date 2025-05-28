package biblioteca.salas.duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired SalaRepository salaRepository;

    //@Override
    public void main(String... args) throws Exception {
        Faker faker = new Faker();
        Random rand = new Random();

        // Generar carreras
        for (int i = 0; i < 50; i++) {
            Carrera carrera = new Carrera();
            carrera.setCodigo(faker.code().asin());
            carrera.setNombre(faker.educator().course());
            carreraRepository.save(carrera);
        }

        List<Carrera> carreras = carreraRepository.findAll();

        // Generar estudiantes
        for (int i = 0; i < 2500; i++) {
            Estudiante estudiante = new Estudiante();
            // estudiante.setId(i + 1);
            estudiante.setRun(faker.idNumber().valid());
            estudiante.setNombres(faker.name().fullName());
            estudiante.setCorreo(faker.internet().emailAddress());
            estudiante.setJornada(faker.options().option("D", "N").charAt(0));
            estudiante.setTelefono(faker.number().numberBetween(100000000, 999999999));
            estudiante.setCodigoCarrera("123");
            estudianteRepository.save(estudiante);
        }
    }

}
