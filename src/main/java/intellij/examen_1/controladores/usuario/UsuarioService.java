package intellij.examen_1.controladores.usuario;

import intellij.examen_1.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;

    public List<Usuario> listAll() {
        return (List<Usuario>) repo.findAll();
    }

    public void save(Usuario Usuario) {
        repo.save(Usuario);
    }

    public Usuario get(Integer id) throws UsuarioNotFoundException {
        Optional<Usuario> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UsuarioNotFoundException(" No se ha encontrado el usuario con el ID: " + id);
    }

    public void delete(Integer id) throws UsuarioNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UsuarioNotFoundException(" No se ha encontrado el usuario con el ID: " + id);
        }
        repo.deleteById(id);
    }
}
