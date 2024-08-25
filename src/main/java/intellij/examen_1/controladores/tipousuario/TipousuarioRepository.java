package intellij.examen_1.controladores.tipousuario;

import intellij.examen_1.modelos.Tipousuario;
import org.springframework.data.repository.CrudRepository;

public interface TipousuarioRepository extends CrudRepository<Tipousuario, Integer> {
    public Long countById(Integer id);
}
