package biblioteca.salas.duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Sala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.models.TipoSala;
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
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private TipoSalaRepository tipoSalaRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random rand = new Random();

        // Generar tipos de salas
        for (int i = 0; i < 2; i++) {
            TipoSala tipoSala = new TipoSala();
            tipoSala.setNombre(faker.gender().binaryTypes());
            tipoSalaRepository.save(tipoSala);
        }

        List<TipoSala> tipoSalas = tipoSalaRepository.findAll();

        // Generar salas
        for (int i = 0; i < 400; i++) {
            Sala sala = new Sala();
            sala.setNombre(faker.university().name());
            sala.setCapacidad(faker.number().numberBetween(10,100));
            sala.setIdInstituto(faker.number().numberBetween(1,10));
            sala.setIdTipo(tipoSalas.get(rand.nextInt(tipoSalas.size())).getIdTipo());
            salaRepository.save(sala);
        }

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
            estudiante.setRun(faker.idNumber().valid().substring(0, 9));
            estudiante.setNombres(faker.name().fullName());
            estudiante.setCorreo(faker.internet().emailAddress());
            estudiante.setJornada(faker.options().option("D", "N").charAt(0));
            estudiante.setTelefono(faker.number().numberBetween(100000000, 999999999));
            estudiante.setCodigoCarrera(carreras.get(rand.nextInt(carreras.size())).getCodigo());
            estudianteRepository.save(estudiante);
        }
    }

}
