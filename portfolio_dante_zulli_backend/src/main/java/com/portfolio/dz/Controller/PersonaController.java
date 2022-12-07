package com.portfolio.dz.Controller;

import com.portfolio.dz.Entity.Persona;
import com.portfolio.dz.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dante
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonaController {

    @Autowired
    IPersonaService IPersonaService;

    @GetMapping("/personas/traer")
    public List<Persona> getPersona() {
        return IPersonaService.getPersona();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona) {
        IPersonaService.savePersona(persona);
        return "La persona fue ingresada con éxito.";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id) {
        IPersonaService.deletePersona(id);
        return "La persona fue eliminada con éxito.";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id, @RequestParam("nombre") String nuevoNombre, @RequestParam("apellido") String nuevoApellido) {
        Persona persona = IPersonaService.findPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        IPersonaService.savePersona(persona);
        return persona;
    }

    @GetMapping("personas/traer/perfil")
    public Persona findPersona() {
        return IPersonaService.findPersona((long) 1);
    }

}