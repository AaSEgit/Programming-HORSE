<section x-data="themeToggle" x-init="$watch('darkMode', value => console.log('Dark mode:', value))">
    <header>
        <h2 class="text-lg font-medium text-gray-900 dark:text-gray-100">
            {{ __('Display Settings') }}
        </h2>

        <p class="mt-1 text-sm text-gray-600 dark:text-gray-400">
            {{ __('Toggle between light and dark mode.') }}
        </p>
    </header>

    <form method="post" action="{{ route('profile.update') }}" class="mt-6 space-y-6">
        @csrf
        @method('patch')

        <div class="flex items-center">
            <x-input-label for="light-toggle" :value="__('Enable Dark Mode')" />

            <!-- Toggle switch structure -->
            <label class="switch ml-3">
                <input type="checkbox" id="light-toggle" name="dark_mode" x-model="darkMode" @click="toggleTheme">
                <span class="slider round"></span>
            </label>
        </div>

        @if (session('status') === 'settings-updated')
            <p
                x-data="{ show: true }"
                x-show="show"
                x-transition
                x-init="setTimeout(() => show = false, 2000)"
                class="text-sm text-gray-600 dark:text-gray-400"
            >{{ __('Settings saved.') }}</p>
        @endif
    </form>
</section>

<style>
/* The switch - the box around the slider */
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 25px;
}

/* Hide default HTML checkbox */
.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

/* The slider */
.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 17px;
  width: 17px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  transition: .4s;
}

/* Style for when the checkbox is checked */
input:checked + .slider {
  background-color: #ff0000; /* Change this to red if you want red when checked */
}

input:checked + .slider:before {
  transform: translateX(34px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
</style>

<script>
  document.addEventListener('alpine:init', () => {
      Alpine.data('themeToggle', () => ({
          darkMode: {{ $user->dark_mode ? 'true' : 'false' }},
          toggleTheme() {
              this.darkMode = !this.darkMode;
              if (this.darkMode) {
                  document.documentElement.classList.add('dark');
              } else {
                  document.documentElement.classList.remove('dark');
              }
              // Save preference to local storage
              localStorage.setItem('darkMode', this.darkMode);
              // You might want to make an AJAX call here to update the user's preference in the database
          },
          init() {
              // Check local storage for saved preference
              if (localStorage.getItem('darkMode') === 'true') {
                  this.darkMode = true;
                  document.documentElement.classList.add('dark');
              }
          }
      }));
  });
</script>
