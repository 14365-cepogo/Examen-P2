package intellij.examen_1.controladores.carrera;

import intellij.examen_1.controladores.departamento.DepartamentoService;
import intellij.examen_1.controladores.estudiante.EstudianteNotFoundException;
import intellij.examen_1.controladores.usuario.UsuarioService;
import intellij.examen_1.modelos.Carrera;
import intellij.examen_1.modelos.Departamento;
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
public class CarreraController {
    @Autowired private CarreraService service;
    @Autowired private DepartamentoService serviceDep;

    @GetMapping("/Carreras")
    public String showCarreraList(Model model) {
        List<Carrera> listCarreras = service.listAll();
        model.addAttribute("listCarreras", listCarreras);
        String cadena = "Carrera";
        model.addAttribute("vista", cadena);
        return "/index";
    }

    @GetMapping("/Carreras/new")
    public String showNewForm(Model model) {
        List<Departamento> listDepartamentos = serviceDep.listAll();
        Carrera Carrera = new Carrera();
        model.addAttribute("Carrera", Carrera);
        model.addAttribute("listDepartamentos", listDepartamentos);
        model.addAttribute("pageTitle", "Crear nueva carrera");
        String cadena = "nuevoCarrera";
        model.addAttribute("vista", cadena);
        return "/index";
    }

    @PostMapping("/Carreras/save")
    public String saveCarrera(Carrera Carrera, RedirectAttributes ra) {
        service.save(Carrera);
        ra.addFlashAttribute("message", "La carrera ha sido guardada correctamente.");
        return "redirect:/Carreras";
    }

    @GetMapping("/Carreras/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Carrera Carrera = service.get(id);
            List<Departamento> listDepartamentos = serviceDep.listAll();
            model.addAttribute("Carrera", Carrera);
            model.addAttribute("listDepartamentos", listDepartamentos);
            model.addAttribute("pageTitle", "Editar carrera");
            String cadena = "editCarrera";
            model.addAttribute("vista", cadena);
            return "/index";
        } catch (CarreraNotFoundException e) {
            ra.addFlashAttribute("message", "La carrera no se ha editado correctamente.");
            return "redirect:/Carreras";
        }
    }

    @GetMapping("/Carreras/delete/{id}")
    public String deleteCarrera(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "La carrera ha sido eliminada correctamente.");
        } catch (CarreraNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/Carreras";
    }

    @GetMapping("/Carreras/detail/{id}")
    public String showCarreraDetail(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Carrera Carrera = service.get(id);
            model.addAttribute("Carrera", Carrera);
            String cadena = "detalleCarrera";
            model.addAttribute("vista", cadena);
            return "/index";

        } catch (CarreraNotFoundException e) {
            ra.addFlashAttribute("message", "La carrera no se ha encontrado.");
            return "redirect:/Carreras";
        }
    }

}
