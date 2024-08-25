package intellij.examen_1.controladores.estudiante;

import intellij.examen_1.modelos.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository repo;

    public List<Estudiante> listAll() {
        return (List<Estudiante>) repo.findAll();
    }

    public void save(Estudiante Estudiante) {
        repo.save(Estudiante);
    }

    public Estudiante get(Integer id) throws EstudianteNotFoundException {
        Optional<Estudiante> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new EstudianteNotFoundException(" No se ha encontrado el estudiante con el ID: " + id);
    }

    public void delete(Integer id) throws EstudianteNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new EstudianteNotFoundException(" No se ha encontrado el estudiante con el ID: " + id);
        }
        repo.deleteById(id);
    }
}
