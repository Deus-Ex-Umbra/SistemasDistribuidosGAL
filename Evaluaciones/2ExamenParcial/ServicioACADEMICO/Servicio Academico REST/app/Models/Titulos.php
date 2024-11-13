<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Titulos extends Model
{
    public $timestamps = false;
    protected $table = 'titulos';
    protected $fillable = ['ci', 'nombre_completo', 'titulo', 'fecha_emision'];
}
