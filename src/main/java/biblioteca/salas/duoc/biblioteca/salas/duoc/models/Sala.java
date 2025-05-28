package biblioteca.salas.duoc.biblioteca.salas.duoc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sala")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column
    private Integer capacidad;

    @Column(nullable = false)
    private Integer idInstituto;

    @Column(nullable = false)
    private Integer idTipo;

}
