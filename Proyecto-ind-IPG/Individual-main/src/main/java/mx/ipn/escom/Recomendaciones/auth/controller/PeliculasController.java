package mx.ipn.escom.Recomendaciones.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
    
    /**
     * Página principal de búsqueda de películas
     */
    @GetMapping
    public String peliculas(Model model, Authentication authentication) {
        // Puedes agregar datos al modelo si necesitas pasar información a la vista
        if (authentication != null) {
            model.addAttribute("usuario", authentication.getName());
        }
        return "peliculas";
    }
    
    /**
     * Página de favoritos del usuario (funcionalidad futura)
     */
    @GetMapping("/favoritos")
    public String favoritos(Model model, Authentication authentication) {
        // Funcionalidad futura para mostrar películas favoritas
        return "peliculas-favoritos"; // Necesitarías crear este template
    }
    
    /**
     * Página de recomendaciones personalizadas (funcionalidad futura)
     */
    @GetMapping("/recomendaciones")
    public String recomendaciones(Model model, Authentication authentication) {
        // Funcionalidad futura para mostrar recomendaciones personalizadas
        return "peliculas-recomendaciones"; // Necesitarías crear este template
    }
}