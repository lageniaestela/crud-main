package com.claujulian.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.claujulian.crud.model.Persona;
import com.claujulian.crud.repository.PersonaRepository;

@Controller
    public class PersonaController {
    
    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Persona> personas = personaRepository.findAll();
        model.addAttribute("personas", personas);
        return "index";
    }

    @GetMapping("/crear")
    public String crearPersonaForm(Model model) {
        model.addAttribute("persona", new Persona());
        return "crear_persona";
    }

    @PostMapping("/guardar")
    public String guardarPersona(@ModelAttribute Persona persona) {
        personaRepository.save(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarPersonaForm(@PathVariable Long id, Model model) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        model.addAttribute("persona", persona);
        return "editar_persona";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPersona(@PathVariable Long id, @ModelAttribute Persona persona) {
        persona.setId(id);
        personaRepository.save(persona);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        personaRepository.delete(persona);
        return "redirect:/";
    }

}
