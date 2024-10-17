<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('games', function (Blueprint $table) {
            $table->id();
            $table->timestamps();
            $table->unsignedBigInteger("game_id")->unique();
            $table->string("user_name");
            $table->string("language");
            $table->foreignId("topic_id");
            $table->string("game_state");
            $table->string("game_status");
            $table->string("game_winner");

            $table->foreign("user_name")->references("user_name")->on("users");
            //$table->foreign("topic_id")->reference("topic_id")->on("topics");
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('games');
    }
};
