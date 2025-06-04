package biblioteca.salas.duoc.biblioteca.salas.duoc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer idEstudiante;

    @Column(nullable = false)
    private Integer codigoSala;

    @Column(nullable = false)
    private Date fechaSolicitada;

    @Column(nullable = false)
    private LocalTime horaSolicitada;

    private LocalTime horaCierre;

    private Integer estado;
}
