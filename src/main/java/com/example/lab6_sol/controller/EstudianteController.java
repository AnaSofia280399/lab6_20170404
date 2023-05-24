package com.example.lab6_sol.controller;

import com.example.lab6_sol.entity.Usuario;
import com.example.lab6_sol.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista")
    public String listaUsuarios(Model model){
        List<Usuario> estudiantes = usuarioRepository.findByRolid(5);
        model.addAttribute("estudiantes", estudiantes);
        return "lista_usuarios";
    }

    @GetMapping("/editar")
    public String editarUsuario(@RequestParam("id") int id, Model model){
        Optional<Usuario> opt = usuarioRepository.findById(id);

        if (opt.isPresent()){
            Usuario estudiante = opt.get();
            model.addAttribute("estudiante", estudiante);
            return "/editarFrm";
        }else {
            return "redirect:/lista_usuarios";
        }

    }

    @GetMapping("/nuevo")
    public String nuevaEstudiante( @ModelAttribute("estudiante") Usuario usuario){
       return "/editarFrm";
    }
    @PostMapping("/guardar")
    public String guardarEstudiante(Model model, RedirectAttributes attr, @ModelAttribute("estudiante") @Validated Usuario estudiante, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.hasFieldErrors("idusuario"));
            model.addAttribute("estudiante", estudiante);
            return "/editarFrm";
        } else {
            if (estudiante.getId() == 0) {
                attr.addFlashAttribute("msg", "Estudiante creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Informacion de estudiante actualizada exitosamente");
            }
            usuarioRepository.save(estudiante);
            return "redirect:/lista_usuarios";
        }
    }



}
