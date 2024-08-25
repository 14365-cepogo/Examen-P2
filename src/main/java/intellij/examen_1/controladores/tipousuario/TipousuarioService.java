package intellij.examen_1.controladores.tipousuario;

import intellij.examen_1.controladores.tipousuario.TipousuarioNotFoundException;
import intellij.examen_1.modelos.Tipousuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipousuarioService {
    @Autowired
    private TipousuarioRepository repo;

    public List<Tipousuario> listAll() {
        return (List<Tipousuario>) repo.findAll();
    }

    public void save(Tipousuario Tipousuario) {
        repo.save(Tipousuario);
    }

    public Tipousuario get(Integer id) throws TipousuarioNotFoundException {
        Optional<Tipousuario> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new TipousuarioNotFoundException(" No se ha encontrado el usuario con el ID: " + id);
    }

    public void delete(Integer id) throws TipousuarioNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new TipousuarioNotFoundException(" No se ha encontrado el usuario con el ID: " + id);
        }
        repo.deleteById(id);
    }
}
