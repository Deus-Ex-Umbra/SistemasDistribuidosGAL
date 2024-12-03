import express from 'express';
import * as facturaController from '../controllers/factura.controller.js';

const router = express.Router();

router.get('/:ci', facturaController.obtenerFactura);
router.put('/:ci', facturaController.editarFactura);
router.put('/estado/:ci', facturaController.editarEstadoFactura);

export default router;

