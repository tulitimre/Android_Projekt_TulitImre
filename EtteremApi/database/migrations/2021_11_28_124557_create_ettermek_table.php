<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEttermekTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ettermek', function (Blueprint $table) {
            $table->integer('id')->primary();
            $table->string('nev', 45)->nullable();
            $table->decimal('ertekeles', 2, 1)->nullable();
            $table->integer('ertekeles_db')->nullable();
            $table->boolean('hazhozszallit')->nullable()->default(true);
            $table->decimal('szal_ar', 4)->nullable();
            $table->integer('minimum_rendeles')->nullable();
            $table->string('cim', 45)->nullable();
            $table->string('telefonszam', 45)->nullable();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('ettermek');
    }
}
