package biblioteca.salas.duoc.biblioteca.salas.duoc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 9, nullable = false)
    private String run;

    @Column(length = 100, nullable = false)
    private String nombres;

    @Column(length = 100)
    private String correo;

    @Column(nullable = false)
    private Character jornada;

    @Column
    private Integer telefono;

    @Column(length = 100, nullable = false)
    private String codigoCarrera;
}
