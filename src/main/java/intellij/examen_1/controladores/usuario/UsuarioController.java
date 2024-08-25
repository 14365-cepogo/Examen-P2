package intellij.examen_1.controladores.usuario;

import intellij.examen_1.controladores.estudiante.EstudianteNotFoundException;
import intellij.examen_1.controladores.tipousuario.TipousuarioService;
import intellij.examen_1.modelos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class UsuarioController {
    @Autowired private UsuarioService service;
    @Autowired private TipousuarioService serviceTip;

    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/Usuarios";

    @GetMapping("/Usuarios")
    public String showUsuarioList(Model model) {
        List<Usuario> listUsuarios = service.listAll();
        model.addAttribute("listUsuarios", listUsuarios);
        String cadena = "Usuario";
        model.addAttribute("vista", cadena);
        return "/index";
    }

    @GetMapping("/Usuarios/new")
    public String showNewForm(Model model) {
        List<Tipousuario> listTipos = serviceTip.listAll();
        Usuario Usuario = new Usuario();
        model.addAttribute("Usuario", Usuario);
        model.addAttribute("listTipos", listTipos);
        model.addAttribute("pageTitle", "Crear nuevo usuario");
        String cadena = "nuevoUsuario";
        model.addAttribute("vista", cadena);
        return "/index";
    }

    @PostMapping("/Usuarios/save")
    public String saveUsuario(Usuario Usuario, RedirectAttributes ra) throws UsuarioNotFoundException {
        MultipartFile fotoFile = Usuario.getFotoFile();

        if (fotoFile != null && !fotoFile.isEmpty()) {
            try {
                // Obtiene el nombre original del archivo
                String originalFileName = fotoFile.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + "/" + originalFileName);

                // Si el archivo no existe, guarda el nuevo archivo
                if (!Files.exists(filePath)) {
                    fotoFile.transferTo(filePath.toFile());
                }

                // Establece el nombre del archivo en el campo 'foto' del usuario
                Usuario.setFoto(originalFileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Si no se subió una nueva imagen, mantenemos la existente
            if (Usuario.getId() != null) {
                Usuario existingUsuario = service.get(Usuario.getId());
                Usuario.setFoto(existingUsuario.getFoto());
            }
        }

        // Manejo de la contraseña
        if (Usuario.getPass() != null && !Usuario.getPass().isEmpty()) {
            // Si se proporciona una nueva contraseña, la establecemos
            Usuario.setPass(Usuario.getPass());
        } else {
            // Si no se proporciona una nueva contraseña, mantenemos la existente
            if (Usuario.getId() != null) {
                Usuario existingUsuario = service.get(Usuario.getId());
                Usuario.setPass(existingUsuario.getPass());
            }
        }

        service.save(Usuario);
        ra.addFlashAttribute("message", "El usuario ha sido guardado correctamente.");
        return "redirect:/Usuarios";
    }




    @GetMapping("/Usuarios/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Usuario Usuario = service.get(id);
            List<Tipousuario> listTipos = serviceTip.listAll();
            model.addAttribute("Usuario", Usuario);
            model.addAttribute("listTipos", listTipos);
            model.addAttribute("pageTitle", "Editar usuario");
            String cadena = "editUsuario";
            model.addAttribute("vista", cadena);
            return "/index";
        } catch (UsuarioNotFoundException e) {
            ra.addFlashAttribute("message", "El usuario no se ha editado correctamente.");
            return "redirect:/Usuarios";
        }
    }

    @GetMapping("/Usuarios/delete/{id}")
    public String deleteUsuario(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "El usuario ha sido eliminado correctamente.");
        } catch (UsuarioNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/Usuarios";
    }

    @GetMapping("/Usuarios/detail/{id}")
    public String showUsuarioDetail(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Usuario Usuario = service.get(id);
            model.addAttribute("Usuario", Usuario);
            String cadena = "detalleUsuario";
            model.addAttribute("vista", cadena);
            return "/index";

        } catch (UsuarioNotFoundException e) {
            ra.addFlashAttribute("message", "El usuario no se ha encontrado.");
            return "redirect:/Usuarios";
        }
    }
}
