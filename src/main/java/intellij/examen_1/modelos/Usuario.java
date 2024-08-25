package intellij.examen_1.modelos;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "usuario", schema = "materia_espe")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USUARIO_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "TIPO_ID")
    private Tipousuario tipo;

    @Column(name = "USER", length = 45)
    private String user;

    @Column(name = "PASS", length = 45)
    private String pass;

    @Column(name = "FOTO", length = 45)
    private String foto;

    @Transient
    private MultipartFile fotoFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tipousuario getTipo() {
        return tipo;
    }

    public void setTipo(Tipousuario tipo) {
        this.tipo = tipo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public MultipartFile getFotoFile() {
        return fotoFile;
    }

    public void setFotoFile(MultipartFile fotoFile) {
        this.fotoFile = fotoFile;
    }

}