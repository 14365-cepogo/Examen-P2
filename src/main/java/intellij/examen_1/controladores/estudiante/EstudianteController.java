package intellij.examen_1.controladores.estudiante;

import intellij.examen_1.controladores.carrera.CarreraService;
import intellij.examen_1.controladores.usuario.UsuarioService;
import intellij.examen_1.modelos.Carrera;
import intellij.examen_1.modelos.Estudiante;
import intellij.examen_1.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EstudianteController {
    @Autowired private EstudianteService service;
    @Autowired private CarreraService serviceCar;
    @Autowired private UsuarioService serviceUsu;

    @GetMapping("/Estudiantes")
    public String showEstudianteList(Model model) {
        List<Estudiante> listEstudiantes = service.listAll();
        model.addAttribute("listEstudiantes", listEstudiantes);
        String cadena = "Estudiante";
        model.addAttribute("vista", cadena);
        return "/index";
    }

    @GetMapping("/Estudiantes/new")
    public String showNewForm(Model model) {
        List<Usuario> listUsuarios = serviceUsu.listAll();
        List<Carrera> listCarreras = serviceCar.listAll();
        Estudiante Estudiante = new Estudiante();
        model.addAttribute("Estudiante", Estudiante);
        model.addAttribute("listUsuarios", listUsuarios);
        model.addAttribute("listCarreras", listCarreras);
        model.addAttribute("pageTitle", "Crear nuevo estudiante");
        String cadena = "nuevoEstudiante";
        model.addAttribute("vista", cadena);
        return "/index";
    }

    @PostMapping("/Estudiantes/save")
    public String saveEstudiante(Estudiante Estudiante, RedirectAttributes ra) {
        service.save(Estudiante);
        ra.addFlashAttribute("message", "El estudiante ha sido guardado correctamente.");
        return "redirect:/Estudiantes";
    }

    @GetMapping("/Estudiantes/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Estudiante Estudiante = service.get(id);
            List<Usuario> listUsuarios = serviceUsu.listAll();
            List<Carrera> listCarreras = serviceCar.listAll();
            model.addAttribute("Estudiante", Estudiante);
            model.addAttribute("listUsuarios", listUsuarios);
            model.addAttribute("listCarreras", listCarreras);
            model.addAttribute("pageTitle", "Editar estudiante");
            String cadena = "editEstudiante";
            model.addAttribute("vista", cadena);
            return "/index";
        } catch (EstudianteNotFoundException e) {
            ra.addFlashAttribute("message", "El estudiante no se ha editado correctamente.");
            return "redirect:/Estudiantes";
        }
    }

    @GetMapping("/Estudiantes/delete/{id}")
    public String deleteEstudiante(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "El estudiante ha sido eliminado correctamente.");
        } catch (EstudianteNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/Estudiantes";
    }

    @GetMapping("/Estudiantes/detail/{id}")
    public String showEstudianteDetail(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Estudiante Estudiante = service.get(id);
            model.addAttribute("Estudiante", Estudiante);
            String cadena = "detalleEstudiante";
            model.addAttribute("vista", cadena);
            return "/index";

        } catch (EstudianteNotFoundException e) {
            ra.addFlashAttribute("message", "El estudiante no se ha encontrado.");
            return "redirect:/Estudiantes";
        }
    }

}
