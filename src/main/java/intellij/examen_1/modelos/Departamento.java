package intellij.examen_1.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "departamento", schema = "materia_espe")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTAMENTO_ID", nullable = false)
    private Integer id;

    @Column(name = "NOMBRE_DEPARTAMENTO", length = 45)
    private String nombreDepartamento;

    @Column(name = "ACRO_DEPARTAMENTO", length = 45)
    private String acroDepartamento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getAcroDepartamento() {
        return acroDepartamento;
    }

    public void setAcroDepartamento(String acroDepartamento) {
        this.acroDepartamento = acroDepartamento;
    }

}