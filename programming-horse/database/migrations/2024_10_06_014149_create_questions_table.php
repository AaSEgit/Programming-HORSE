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
        Schema::create('questions', function (Blueprint $table) {
            $table->id();
            $table->timestamps();
            $table->bigIntenger("question_id");
            $table->string("language");
            $table->string("topic_id");
            $table->string("question_prompt");
            $table->string("correct_answer");
            $table->string("incorrect_answer_1");
            $table->string("incorrect_answer_2");
            $table->string("incorrect_answer_3");
            $table->string("incorrect_answer_5");
            $table->bool("validated");

            $table->foreign("topic_id")->reference("topic_id")->on("topics");
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('questions');
    }
};
