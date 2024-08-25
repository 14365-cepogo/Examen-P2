package intellij.examen_1.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "tipousuario", schema = "materia_espe")
public class Tipousuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIPO_ID", nullable = false)
    private Integer id;

    @Column(name = "NOMBRE_TIPO", length = 45)
    private String nombreTipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

}