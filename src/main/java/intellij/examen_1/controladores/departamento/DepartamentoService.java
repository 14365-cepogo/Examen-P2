package intellij.examen_1.controladores.departamento;

import intellij.examen_1.controladores.departamento.DepartamentoNotFoundException;
import intellij.examen_1.modelos.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository repo;

    public List<Departamento> listAll() {
        return (List<Departamento>) repo.findAll();
    }

    public void save(Departamento Departamento) {
        repo.save(Departamento);
    }

    public Departamento get(Integer id) throws DepartamentoNotFoundException {
        Optional<Departamento> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new DepartamentoNotFoundException(" No se ha encontrado el usuario con el ID: " + id);
    }

    public void delete(Integer id) throws DepartamentoNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new DepartamentoNotFoundException(" No se ha encontrado el usuario con el ID: " + id);
        }
        repo.deleteById(id);
    }
}
