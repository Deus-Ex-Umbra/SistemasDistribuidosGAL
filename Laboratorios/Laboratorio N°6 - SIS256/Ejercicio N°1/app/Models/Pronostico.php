<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Pronostico extends Model
{
    public $timestamps = false;

    protected $table = 'pronosticos';

    protected $fillable = [
        'fecha',
        'temperatura'
    ];
}
