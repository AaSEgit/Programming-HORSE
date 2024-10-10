<?php

use App\Http\Controllers\ProfileController;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
})->name('welcome');

Route::get('/topics', function () {
    return view('topics');
})->name('topics');

Route::get('/playgame', function () {
    return view('playgame');
})->name('playgame');

Route::get('/index', function () {
    return view('welcome');
});

Route::get('/rules', function () {
    return view('rules');
})->name('rules');

Route::get('/welcome', function () {
    return view('welcome');
});

Route::get('/about', function () {
    return view('about');
});

Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});

Route::post('/submit-answer', [RoundController::class, 'store']); 

require __DIR__.'/auth.php';
