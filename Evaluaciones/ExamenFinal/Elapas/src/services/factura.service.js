import { Factura } from '../models/factura.js';

export const obtenerFactura = async (ci) => {
    try {
        const factura = await Factura.findOne({ where: { ci } });
        return factura;
    } catch (error) {
        console.log(error);
    }
};

export const editarFactura = async (data, ci) => {
    try {
        const factura = await Factura.update(data, { where: { ci } });
        return factura;
    } catch (error) {
        console.log(error);
    }
};

export const editarEstadoFactura = async (estado, ci) => {
    try {
        const factura = await Factura.update({ pagado: estado }, { where: { ci } });
        return factura;
    } catch (error) {
        console.log(error);
    }
}