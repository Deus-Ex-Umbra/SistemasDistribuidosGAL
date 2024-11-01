<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Cotizacion;

class CotizacionController extends Controller
{
    public function index()
    {
        $cotizaciones = Cotizacion::all();
        return response()->json($cotizaciones);
    }

    public function store(Request $request)
    {
        $cotizacion = Cotizacion::create($request->all());
        return response()->json($cotizacion, 201);
    }

    public function show(string $id)
    {
        $cotizacion = Cotizacion::find($id);
        return response()->json($cotizacion, 200);
    }

    public function update(Request $request, string $id)
    {
        $cotizacion = Cotizacion::find($id);
        $cotizacion->update($request->all());
        return response()->json($cotizacion, 200);
    }

    public function destroy(string $id)
    {
        $cotizacion = Cotizacion::find($id);
        $cotizacion->delete();
        return response()->json(null, 200);
    }

    public function getByFecha(Request $request)
    {
        $cotizaciones = Cotizacion::where('fecha', $request->fecha)->get();
        return response()->json($cotizaciones, 200);
    }
}
