<?php

namespace App\Http\Controllers;

use App\Models\Titulos;
use Illuminate\Http\Request;

class TitulosController extends Controller
{
    public function index() {
        $titulos = Titulos::all();
        return response()->json($titulos);
    }

    public function show(string $id) {
        $titulo = Titulos::find($id);
        return response()->json($titulo, 200);
    }

    public function store(Request $request) {
        $titulo = Titulos::create($request->all());
        return response()->json($titulo, 201);
    }

    public function update(Request $request, string $id) {
        $titulo = Titulos::find($id);
        $titulo->update($request->all());
        return response()->json($titulo, 200);
    }

    public function destroy(string $id)
    {
        $cotizacion = Titulos::find($id);
        $cotizacion->delete();
        return response()->json(null, 200);
    }
}
