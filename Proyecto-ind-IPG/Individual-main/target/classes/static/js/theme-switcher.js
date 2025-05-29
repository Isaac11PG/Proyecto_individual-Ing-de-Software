// Theme Switcher JavaScript
(function() {
    'use strict';

    // Configuración
    const STORAGE_KEY = 'theme-preference';
    const THEME_LIGHT = 'light';
    const THEME_DARK = 'dark';

    // Elementos
    let themeToggleBtn = null;

    // Obtener tema guardado o detectar preferencia del sistema
    function getStoredTheme() {
        return localStorage.getItem(STORAGE_KEY);
    }

    function getSystemTheme() {
        return window.matchMedia('(prefers-color-scheme: dark)').matches ? THEME_DARK : THEME_LIGHT;
    }

    function getCurrentTheme() {
        return getStoredTheme() || getSystemTheme();
    }

    // Aplicar tema
    function applyTheme(theme) {
        document.documentElement.setAttribute('data-theme', theme);
        updateThemeToggleIcon(theme);
        localStorage.setItem(STORAGE_KEY, theme);
    }

    // Actualizar icono del botón
    function updateThemeToggleIcon(theme) {
        if (!themeToggleBtn) return;
        
        const icon = themeToggleBtn.querySelector('.icon');
        if (icon) {
            icon.textContent = theme === THEME_DARK ? '☀️' : '🌙';
            icon.setAttribute('title', theme === THEME_DARK ? 'Cambiar a tema claro' : 'Cambiar a tema oscuro');
        }
    }

    // Alternar tema
    function toggleTheme() {
        const currentTheme = document.documentElement.getAttribute('data-theme') || THEME_LIGHT;
        const newTheme = currentTheme === THEME_DARK ? THEME_LIGHT : THEME_DARK;
        applyTheme(newTheme);
    }

    // Crear botón de cambio de tema
    function createThemeToggleButton() {
        const button = document.createElement('button');
        button.className = 'theme-toggle';
        button.setAttribute('aria-label', 'Cambiar tema');
        
        const icon = document.createElement('span');
        icon.className = 'icon';
        button.appendChild(icon);
        
        button.addEventListener('click', toggleTheme);
        
        // Insertar el botón en el documento
        document.body.appendChild(button);
        
        return button;
    }

    // Inicializar
    function init() {
        // Aplicar tema inicial inmediatamente para evitar flash
        const initialTheme = getCurrentTheme();
        applyTheme(initialTheme);

        // Crear botón cuando el DOM esté listo
        if (document.readyState === 'loading') {
            document.addEventListener('DOMContentLoaded', function() {
                themeToggleBtn = createThemeToggleButton();
                updateThemeToggleIcon(initialTheme);
            });
        } else {
            themeToggleBtn = createThemeToggleButton();
            updateThemeToggleIcon(initialTheme);
        }

        // Escuchar cambios en la preferencia del sistema
        window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', function(e) {
            if (!getStoredTheme()) {
                applyTheme(e.matches ? THEME_DARK : THEME_LIGHT);
            }
        });
    }

    // Función global para uso manual
    window.setTheme = applyTheme;
    window.toggleTheme = toggleTheme;
    window.getCurrentTheme = getCurrentTheme;

    // Inicializar inmediatamente
    init();
})();