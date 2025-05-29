package mx.ipn.escom.Recomendaciones.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Configuración moderna para CSRF (alternativa a .csrf().disable())
            .csrf(AbstractHttpConfigurer::disable)
            
            // Configuración de autorizaciones
            .authorizeHttpRequests(auth -> auth
                // Endpoints públicos
                .requestMatchers(
                    "/api/auth/**",
                    "/api/register",
                    "/register", 
                    "/login",
                    "/css/**", 
                    "/js/**", 
                    "/images/**"
                ).permitAll()
                
                // Área de administración
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                
                // Rutas autenticadas
                .requestMatchers(
                    "/perfil",
                    "/home",
                    "/libros"
                ).authenticated()
                
                // Todas las demás rutas permitidas (ajustar según necesidades)
                .anyRequest().permitAll()
            )
            
            // Configuración de formulario de login
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            
            // Configuración de logout
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}