package biblioteca.salas.duoc.biblioteca.salas.duoc;

import biblioteca.salas.duoc.biblioteca.salas.duoc.models.*;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.*;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

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
    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random rand = new Random();

        crearTiposSalas(2);
        crearSalas(100);
        crearCarreras(20);
        crearEstudiante(1000);
        crearReserva(1200);
    }

    private boolean crearTiposSalas(int cantidadTiposSalas) {

        try {
            Faker faker = new Faker();
            Set<String> tiposSalasGeneradas = new HashSet<>();

            while (tiposSalasGeneradas.size() < cantidadTiposSalas) {
                String nombreSala = faker.gender().binaryTypes();

                if (!tiposSalasGeneradas.contains(nombreSala)) {
                    tiposSalasGeneradas.add(nombreSala);
                    TipoSala tipoSala = new TipoSala();
                    tipoSala.setNombre(nombreSala);
                    tipoSalaRepository.save(tipoSala);
                }
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

    private boolean crearSalas(int cantidadSalas) {

        try {
            Faker faker = new Faker();
            Random rand = new Random();

            List<TipoSala> tipoSalas = tipoSalaRepository.findAll();

            Set<String> tiposSalasGeneradas = new HashSet<>();

            while (tiposSalasGeneradas.size() < cantidadSalas) {
                String nombreTipoSala = faker.university().name();

                if (!tiposSalasGeneradas.contains(nombreTipoSala)) {
                    tiposSalasGeneradas.add(nombreTipoSala);
                    Sala sala = new Sala();
                    sala.setNombre(nombreTipoSala);
                    sala.setCapacidad(faker.number().numberBetween(10,100));
                    sala.setIdInstituto(faker.number().numberBetween(1,14));
                    sala.setTipoSala(tipoSalas.get(rand.nextInt(tipoSalas.size())));
                    salaRepository.save(sala);
                }
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private boolean crearCarreras(int cantidadCarreras) {

        try {
            Faker faker = new Faker();

            Set<String> nombresCarrerasGenerados = new HashSet<>();

            while (nombresCarrerasGenerados.size() < cantidadCarreras) {
                String nombreCarrera = faker.educator().course();

                if (!nombresCarrerasGenerados.contains(nombreCarrera)) {
                    nombresCarrerasGenerados.add(nombreCarrera);
                    Carrera carrera = new Carrera();
                    carrera.setCodigo(faker.code().asin());
                    carrera.setNombre(nombreCarrera);
                    carreraRepository.save(carrera);
                }
            }

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    private boolean crearReserva(int cantidadReservas) {
        try {
            if (cantidadReservas <= 0) {
                throw new Exception("Cantidad de reservas invalida");
            }

            List<Sala> salas = salaRepository.findAll();
            List<Estudiante> estudiantes = estudianteRepository.findAll();
            Random rand = new Random();
            Faker faker = new Faker();

            for (int i = 0; i < cantidadReservas; i++) {
                Reserva reserva = new Reserva();
                reserva.setEstudiante(estudiantes.get(rand.nextInt(estudiantes.size())));
                reserva.setSala(salas.get(rand.nextInt(salas.size())));
                reserva.setFechaSolicitada(new Date());
                reserva.setHoraSolicitada(new Date());
                reserva.setHoraCierre(new Date(System.currentTimeMillis() + faker.number().numberBetween(3600000, 7200000)));
                reserva.setEstado(faker.number().numberBetween(1,3));
                reservaRepository.save(reserva);
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
