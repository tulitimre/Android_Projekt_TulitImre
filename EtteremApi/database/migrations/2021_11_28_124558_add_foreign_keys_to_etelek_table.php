<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class AddForeignKeysToEtelekTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('etelek', function (Blueprint $table) {
            $table->foreign(['eteltipusok_id'], 'fk_etelek_eteltipusok')->references(['id'])->on('eteltipusok')->onUpdate('NO ACTION')->onDelete('NO ACTION');
            $table->foreign(['ettermek_id'], 'fk_etelek_ettermek1')->references(['id'])->on('ettermek')->onUpdate('NO ACTION')->onDelete('NO ACTION');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('etelek', function (Blueprint $table) {
            $table->dropForeign('fk_etelek_eteltipusok');
            $table->dropForeign('fk_etelek_ettermek1');
        });
    }
}
