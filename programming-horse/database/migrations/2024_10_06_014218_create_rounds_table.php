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
        Schema::create('rounds', function (Blueprint $table) {
            $table->id();
            $table->timestamps();
            $table->bigIncrements("round_num")->primary();
            $table->bigIntenger("game_id")->unqiue();
            $table->bigInteger("question_id");
            $table->string("answer_selected");
            $table->string("round_winner");

            $table->foreign("game_id")->reference("game_id")->on("games");
            $table->foreign("question_id")->references("question_id")->on("questions");
    });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('rounds');
    }
};
