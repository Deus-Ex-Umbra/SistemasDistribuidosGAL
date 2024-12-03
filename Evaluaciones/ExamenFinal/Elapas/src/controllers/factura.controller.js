import * as facturaService from '../services/factura.service.js';

export const obtenerFactura = async (req, res) => {
    const { ci } = req.params;
    const factura = await facturaService.obtenerFactura(ci);
    res.json(factura);
};

export const editarFactura = async (req, res) => {
    const { ci } = req.params;
    const data = req.body;
    const factura = await facturaService.editarFactura(data, ci);
    res.json(factura);
}

export const editarEstadoFactura = async (req, res) => {
    const { ci } = req.params;
    const { estado } = req.body;
    const factura = await facturaService.editarEstadoFactura(estado, ci);
    res.json(factura);
}

