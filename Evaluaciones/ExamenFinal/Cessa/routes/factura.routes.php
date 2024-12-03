<?php 

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\FacturaController;

Route::get('/factura/{ci}', [FacturaController::class, 'obtenerFactura']);
Route::put('/factura/{ci}', [FacturaController::class, 'editarFactura']);
Route::put('/factura/{ci}/estado/', [FacturaController::class, 'editarEstadoFactura']);