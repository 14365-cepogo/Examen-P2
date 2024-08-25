package intellij.examen_1.controladores.carrera;

import intellij.examen_1.modelos.Carrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {
    @Autowired
    private CarreraRepository repo;

    public List<Carrera> listAll() {
        return (List<Carrera>) repo.findAll();
    }

    public void save(Carrera Carrera) {
        repo.save(Carrera);
    }

    public Carrera get(Integer id) throws CarreraNotFoundException {
        Optional<Carrera> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new CarreraNotFoundException(" No se ha encontrado el usuario con el ID: " + id);
    }

    public void delete(Integer id) throws CarreraNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new CarreraNotFoundException(" No se ha encontrado el usuario con el ID: " + id);
        }
        repo.deleteById(id);
    }
}
