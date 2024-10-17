<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class GameController extends Controller
{
    // Get a specific game by ID
    public function show($id)
    {
        return Game::findOrFail($id);
    }

    // Create a new game
    public function store(Request $request)
    {
        $request->validate([
            'user_name' => 'required|string|max:255',
            'language' => 'required|string|max:255',
            'topic' => 'required|string|max:255',
            'game_state' => 'required|in:in_progress,completed',
            'game_status' => 'required|string|max:255', // e.g., "HOR"
        ]);

        $game = Game::create($request->all()); // Create and return the new game
        return response()->json($game, 201); // Return the created game with a 201 status
    }

    // Update an existing game (to continue)
    public function update(Request $request, $id)
    {
        $game = Game::findOrFail($id);
        
        $request->validate([
            'game_state' => 'sometimes|required|in:in_progress,completed',
            'game_status' => 'sometimes|required|string|max:255',
            'game_winner' => 'nullable|string|in:user,computer', // Specify who won the game
        ]);

        // Update only if the game state is 'completed' to set the winner
        if ($request->has('game_state') && $request->game_state === 'completed') {
            $request->validate([
                'game_winner' => 'required|string|in:user,computer', // Winner must be specified
            ]);
        }

        $game->update($request->only('game_state', 'game_status', 'game_winner'));
        return $game;
    }

    // Restart a game
    public function restart($id)
    {
        $game = Game::findOrFail($id);

        // Reset game fields to start a new game
        $game->game_state = 'in_progress';
        $game->game_status = ''; // Reset to initial status
        $game->game_winner = null; // No winner yet

        $game->save();
        return $game;
    }
}
