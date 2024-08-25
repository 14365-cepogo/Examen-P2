package intellij.examen_1.controladores.estudiante;

import intellij.examen_1.modelos.Estudiante;
import org.springframework.data.repository.CrudRepository;

public interface EstudianteRepository extends CrudRepository<Estudiante, Integer> {
    public Long countById(Integer id);
}
