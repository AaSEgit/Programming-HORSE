import './bootstrap';

import Alpine from 'alpinejs';

window.Alpine = Alpine;

Alpine.start();

// DARK MODE TOGGLE BUTTON
document.addEventListener('alpine:init', () => {
    Alpine.data('themeToggle', () => ({
        darkMode: {{ $user->dark_mode ? 'true' : 'false' }},
        toggleTheme() {
            this.darkMode = !this.darkMode;
            this.applyTheme();
            localStorage.setItem('darkMode', this.darkMode);
            // You might want to make an AJAX call here to update the user's preference in the database
        },
        applyTheme() {
            const root = document.documentElement;
            if (this.darkMode) {
                root.style.setProperty('--background-color', '#1a1a1a');
                root.style.setProperty('--text-color', '#ffffff');
                root.style.setProperty('--grid-background', '#2a2a2a');
                root.style.setProperty('--item-background', '#333333');
                root.style.setProperty('--item-text-color', '#ffffff');
                root.style.setProperty('--button-background', '#ffffff');
                root.style.setProperty('--button-text-color', '#000000');
            } else {
                root.style.setProperty('--background-color', '#ffffff');
                root.style.setProperty('--text-color', '#000000');
                root.style.setProperty('--grid-background', '#f0f0f0');
                root.style.setProperty('--item-background', '#ffffff');
                root.style.setProperty('--item-text-color', '#000000');
                root.style.setProperty('--button-background', '#000000');
                root.style.setProperty('--button-text-color', '#ffffff');
            }
        },
        init() {
            const savedTheme = localStorage.getItem('darkMode');
            if (savedTheme !== null) {
                this.darkMode = savedTheme === 'true';
            } else {
                this.darkMode = {{ $user->dark_mode ? 'true' : 'false' }};
            }
            this.applyTheme();
        }
    }));
});