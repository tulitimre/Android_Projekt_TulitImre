<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToOsszehasonlithatoTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('osszehasonlithato', function (Blueprint $table) {
            $table->foreign(['etelek_id'], 'fk_ugyanaz_etelek1')->references(['id'])->on('etelek')->onUpdate('NO ACTION')->onDelete('NO ACTION');
            $table->foreign(['etelek_id1'], 'fk_ugyanaz_etelek2')->references(['id'])->on('etelek')->onUpdate('NO ACTION')->onDelete('NO ACTION');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('osszehasonlithato', function (Blueprint $table) {
            $table->dropForeign('fk_ugyanaz_etelek1');
            $table->dropForeign('fk_ugyanaz_etelek2');
        });
    }
}
