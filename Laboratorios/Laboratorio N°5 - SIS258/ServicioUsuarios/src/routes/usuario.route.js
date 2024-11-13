const express = require('express');
const router = express.Router();
const UsuarioController = require('../controllers/UsuarioController');
const verificarToken = require('../utils/VerificarToken');

router.post('/registrar', UsuarioController.registrar);
router.post('/login', UsuarioController.registrar);
router.get('/perfil', verificarToken, async (req, res) => {
    res.status(200).send(req.userId);
});

module.exports = router;