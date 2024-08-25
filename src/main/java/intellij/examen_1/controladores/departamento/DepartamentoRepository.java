package intellij.examen_1.controladores.departamento;

import intellij.examen_1.modelos.Departamento;
import org.springframework.data.repository.CrudRepository;

public interface DepartamentoRepository extends CrudRepository<Departamento, Integer> {
    public Long countById(Integer id);
}
