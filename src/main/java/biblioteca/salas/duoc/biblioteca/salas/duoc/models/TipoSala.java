package biblioteca.salas.duoc.biblioteca.salas.duoc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tipo_sala")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoSala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;

    @Column(nullable = false)
    private String nombre;
}
