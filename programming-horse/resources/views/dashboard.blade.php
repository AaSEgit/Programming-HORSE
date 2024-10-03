<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 dark:text-gray-200 leading-tight text-align: center">
            {{ __('Main Menu') }}
        </h2>
    </x-slot>

    <div class="py-12">
        <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
            <div class="bg-white dark:bg-gray-800 overflow-hidden shadow-sm sm:rounded-lg">
                <div class="p-6 text-gray-900 dark:text-gray-100">
                    <x-primary-button>Play a Game</x-primary-button>
                    <x-primary-button>View Rules</x-primary-button>
                    <x-primary-button>View List of Topics</x-primary-button>
                </div>
            </div>
        </div>
    </div>
</x-app-layout>
