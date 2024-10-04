<section>
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
                <input type="checkbox" id="light-toggle" name="dark_mode" @if(old('dark_mode', $user->dark_mode)) checked @endif>
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
