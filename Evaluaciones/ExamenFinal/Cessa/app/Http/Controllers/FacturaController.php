<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Factura;

class FacturaController extends Controller
{
    public static function obtenerFactura($_ci) {
        $factura = Factura::where('ci', $_ci)->first();
        if ($factura == null) {
            return response()->json(['message' => 'No se encontró la factura'], 404);
        }
        return response()->json($factura, 200);
    }

    public static function editarFactura(Request $request, $_ci) {
        $factura = Factura::where('ci', $_ci)->first();
        if ($factura == null) {
            return response()->json(['message' => 'No se encontró la factura'], 404);
        }
        $factura->update($request->all());
        return response()->json($factura, 200);
    }

    public static function editarEstadoFactura($_estado, $ci) {
        $factura = Factura::where('ci', $_ci)->first();
        if ($factura == null) {
            return response()->json(['message' => 'No se encontró la factura'], 404);
        }
        $factura->update(['pagado' => $_estado]);
        return response()->json($factura, 200);
    }
}
