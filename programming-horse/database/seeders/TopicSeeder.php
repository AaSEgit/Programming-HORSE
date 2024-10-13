<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class TopicSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $topics = [
            ['topic_id' => 1, 'topic_prompt' => 'Data Types'],
            ['topic_id' => 2, 'topic_prompt' => 'Object Oriented Programming'],
            ['topic_id' => 3, 'topic_prompt' => 'Data Structures'],
            ['topic_id' => 4, 'topic_prompt' => 'Variable Types and Declarations'],
        ];

        // Insert the topics into the database
        DB::table('topics')->insert($topics);
    }
}
