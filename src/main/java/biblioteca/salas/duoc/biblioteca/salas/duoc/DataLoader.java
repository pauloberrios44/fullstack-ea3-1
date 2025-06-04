package biblioteca.salas.duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Carrera;
import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Estudiante;
import biblioteca.salas.duoc.biblioteca.salas.duoc.models.Sala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.models.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.*;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    @Transactional
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random rand = new Random();

        crearTiposSalas(2);

        List<TipoSala> tipoSalas = tipoSalaRepository.findAll();

        // Generar salas
        for (int i = 0; i < 100; i++) {
            Sala sala = new Sala();
            sala.setNombre(faker.university().name());
            sala.setCapacidad(faker.number().numberBetween(10,100));
            sala.setIdInstituto(faker.number().numberBetween(1,10));
            sala.setTipoSala(tipoSalas.get(rand.nextInt(tipoSalas.size())));
            salaRepository.save(sala);
        }

        // Generar carreras
        for (int i = 0; i < 20; i++) {
            Carrera carrera = new Carrera();
            carrera.setCodigo(faker.code().asin());
            carrera.setNombre(faker.educator().course());
            carreraRepository.save(carrera);
        }

        // Generar estudiantes
        crearEstudiante(1000);
    }


    /**
     * Crea una cantidad específica de tipos de salas en la base de datos
     * usando datos falsos con DataFaker
     *
     * @param cantidadTiposSalas
     * @return true si se crean correctamente; false si ocurre una excepción
     */
    private boolean crearTiposSalas(int cantidadTiposSalas) {

        try {
            Faker faker = new Faker();

            for (int i = 0; i < cantidadTiposSalas; i++) {
                TipoSala tipoSala = new TipoSala();
                tipoSala.setNombre(faker.gender().binaryTypes());
                tipoSalaRepository.save(tipoSala);
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private boolean crearEstudiante(int cantidadEstudiante) {
        try {
            Faker faker = new Faker();
            Random rand = new Random();
            List<Carrera> carreras = carreraRepository.findAll();

            Set<String> runGenerados = new HashSet<>();

            while (runGenerados.size() < cantidadEstudiante) {
                String run = faker.idNumber().valid().substring(0, 9);

                if (!runGenerados.contains(run)) {
                    runGenerados.add(run);
                    Estudiante estudiante = new Estudiante();
                    estudiante.setRun(run);
                    estudiante.setNombres(faker.name().fullName());
                    estudiante.setCorreo(faker.internet().emailAddress());
                    estudiante.setJornada(faker.options().option("D", "N").charAt(0));
                    estudiante.setTelefono(faker.number().numberBetween(100000000, 999999999));
                    estudiante.setCarrera(carreras.get(rand.nextInt(carreras.size())));
                    estudianteRepository.save(estudiante);
                }
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
