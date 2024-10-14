<!-- resources/views/layouts/app.blade.php -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>@yield('title')</title>
    <link rel="stylesheet" href="{{ asset('css/style.css') }}">
    <script>
            // Apply saved theme on page load
            window.addEventListener('DOMContentLoaded', () => {
                const savedTheme = localStorage.getItem('theme');
                if (savedTheme === 'dark') {
                    document.documentElement.classList.add('dark-mode');
                }
            });
        </script>

        <style>
            /* Light mode (default) */
            body {
                background-color: white;
                color: black;
            }

            /* Dark mode */
            .dark-mode body {
                background-color: #333;
                color: white;
            }
        </style>
</head>
<body>
    @yield('content')
</body>
</html>