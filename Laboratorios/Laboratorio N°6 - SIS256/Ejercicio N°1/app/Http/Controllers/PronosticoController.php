<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Pronostico;

class PronosticoController extends Controller
{
    public static function createPronostico(Request $request)
    {
        $validate = $request->validate([
            'fecha' => 'required|date',
            'temperatura' => 'required|numeric'
        ]);

        $pronostico = Pronostico::create($validate);
        return response()->json($pronostico, 201);
    }

    public static function getPronosticos()
    {
        $pronosticos = Pronostico::all();
        return response()->json($pronosticos, 200);
    }

    public static function getPronostico($id)
    {
        $pronostico = Pronostico::find($id);
        if ($pronostico) {
            return response()->json($pronostico, 200);
        } else {
            return response()->json(['message' => 'Pronostico no encontrado'], 404);
        }
    }

    public static function updatePronostico(Request $request, $id)
    {
        $pronostico = Pronostico::find($id);
        if ($pronostico) {
            $validate = $request->validate([
                'fecha' => 'required|date',
                'temperatura' => 'required|numeric'
            ]);

            $pronostico->update($validate);
            return response()->json($pronostico, 200);
        } else {
            return response()->json(['message' => 'Pronostico no encontrado'], 404);
        }
    }

    public static function deletePronostico($id)
    {
        $pronostico = Pronostico::find($id);
        if ($pronostico) {
            $pronostico->delete();
            return response()->json(['message' => 'Pronostico eliminado'], 200);
        } else {
            return response()->json(['message' => 'Pronostico no encontrado'], 404);
        }
    }
}
