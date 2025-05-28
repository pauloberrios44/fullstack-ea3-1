package biblioteca.salas.duoc.biblioteca.salas.duoc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrera")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrera {

    @Id
    @Column(length = 100)
    private String codigo;

    @Column(length = 100)
    private String nombre;

}
