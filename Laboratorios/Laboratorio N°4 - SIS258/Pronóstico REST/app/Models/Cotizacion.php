<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Cotizacion extends Model
{
    public $timestamps = false;
    protected $table = 'cotizacion';
    protected $fillable = ['fecha', 'cotizacion', 'cotizacion_oficial'];
}
