<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\CotizacionController;

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');

Route::apiResource('cotizaciones', CotizacionController::class);
Route::post('/cotizaciones/fecha', [CotizacionController::class, 'getByDate']);
