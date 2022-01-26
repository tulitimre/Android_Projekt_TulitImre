<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Etel extends Model
{
    use HasFactory;

    protected $table = 'etelek';
    protected $fillable = ['id', 'nev', 'eteltipusok_id', 'ettermek_id', 'ar', 'mennyiseg'];
    public $timestamps = false;
}
