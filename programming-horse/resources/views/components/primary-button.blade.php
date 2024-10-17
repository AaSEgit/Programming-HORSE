<button {{ $attributes->merge(['type' => 'submit', 'class' => 'inline-flex items-center px-6 py-3 bg-white dark:bg-gray-800 border border-transparent rounded-md font-semibold text-lg text-gray-800 dark:text-white uppercase tracking-widest hover:bg-gray-700 hover:text-white dark:hover:bg-gray-100 dark:hover:text-gray-800 focus:bg-gray-700 dark:focus:bg-white active:bg-gray-900 dark:active:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 dark:focus:ring-offset-gray-800 transition ease-in-out duration-150']) }}>
    {{ $slot }}
</button>

