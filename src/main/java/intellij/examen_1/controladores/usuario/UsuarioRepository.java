package intellij.examen_1.controladores.usuario;

import intellij.examen_1.modelos.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    public Long countById(Integer id);
}
