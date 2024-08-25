package intellij.examen_1.modelos;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "carrera", schema = "materia_espe")
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARRERA_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "DEPARTAMENTO_ID")
    private Departamento departamento;

    @Column(name = "NOMBRE_CARRERA", length = 45)
    private String nombreCarrera;

    @Column(name = "NUM_NIVEL")
    private Integer numNivel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Integer getNumNivel() {
        return numNivel;
    }

    public void setNumNivel(Integer numNivel) {
        this.numNivel = numNivel;
    }

}