<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateOsszehasonlithatoTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('osszehasonlithato', function (Blueprint $table) {
            $table->integer('etelek_id');
            $table->integer('etelek_id1')->index('fk_ugyanaz_etelek2_idx');
            $table->decimal('ar_per_menny', 6)->nullable();
            $table->decimal('ar_per_menny1', 6)->nullable();

            $table->primary(['etelek_id', 'etelek_id1']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('osszehasonlithato');
    }
}
