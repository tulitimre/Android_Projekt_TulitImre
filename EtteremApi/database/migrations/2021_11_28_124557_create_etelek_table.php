<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEtelekTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('etelek', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->string('nev', 1000)->nullable();
            $table->integer('eteltipusok_id')->index('fk_etelek_eteltipusok_idx');
            $table->integer('ettermek_id')->index('fk_etelek_ettermek1_idx');
            $table->decimal('ar', 6)->nullable();
            $table->integer('mennyiseg')->nullable();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('etelek');
    }
}
