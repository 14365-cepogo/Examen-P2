package intellij.examen_1.controladores.carrera;

import intellij.examen_1.modelos.Carrera;
import org.springframework.data.repository.CrudRepository;

public interface CarreraRepository extends CrudRepository<Carrera, Integer> {
    public Long countById(Integer id);
}
