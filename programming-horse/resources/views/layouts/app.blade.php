<!DOCTYPE html>
<html lang="{{ str_replace('_', '-', app()->getLocale()) }}" 
x-data="{ darkMode: false }" 
    x-init="(function() {
        // Set dark mode based on user's system preference or localStorage
        if (!('darkMode' in localStorage) && window.matchMedia('(prefers-color-scheme: dark)').matches) {
            localStorage.setItem('darkMode', JSON.stringify(true));
        }
        darkMode = JSON.parse(localStorage.getItem('darkMode'));
        if (darkMode) {
            document.documentElement.classList.add('dark');
        } else {
            document.documentElement.classList.remove('dark');
        }

        // Watch for changes in darkMode and update localStorage and class accordingly
        $watch('darkMode', value => {
            localStorage.setItem('darkMode', JSON.stringify(value));
            if (value) {
                document.documentElement.classList.add('dark');
            } else {
                document.documentElement.classList.remove('dark');
            }
        });
    })()">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="csrf-token" content="{{ csrf_token() }}">

        <title>Programming HORSE</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Urbanist:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
        <link href="{{ asset('css/app.css') }}" rel="stylesheet">
        <!-- Scripts -->
        @vite(['resources/css/app.css', 'resources/js/app.js'])



    </head>
    <body style="font-family: 'Urbanist';" class="min-h-screen bg-gray-100 dark:bg-gray-900">
        @include('layouts.navigation')
    
            <!-- Page Heading -->
            @isset($header)
                <header class="bg-white dark:bg-gray-800 shadow">
                    
                    <div class="max-w-7xl mx-auto py-6 px-4 sm:px-6 lg:px-8">
                        {{ $header }}
                    </div>
                </header>
            @endisset

            <!-- Page Content -->
            <main>
                {{ $slot }}
            </main>
        </div>
    </body>

</html>
