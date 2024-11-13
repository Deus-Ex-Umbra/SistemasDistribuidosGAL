const Usuario = require('../models/Usuario');
const jwt = require('jsonwebtoken');
const config = require('../../config/config');
const { enviarMensaje } = require('../services/rabbitmq');
const bcrypt = require('bcrypt');

exports.registrar = async (req, res) => {
    try {
        const { nombre, correo, password } = req.body;
        const usuario = new Usuario({ nombre, correo, password });
        await usuario.save();

        const token = jwt.sign(
            {
                id: usuario._id,
                nombre: usuario.nombre,
                correo: usuario.correo
            },
            config.jwtSecret,
            { expiresIn: '24h' }
        );

        const mensaje = JSON.stringify({
            to: usuario.correo,
            subject: 'Bienvenido a la API de Node.js',
            html: `<h1>Hola ${usuario.nombre}</h1><p>Gracias por registrarte en nuestra API</p>`
        });
        await enviarMensaje(mensaje);
    } catch (error) {
        return res.status(400).json({ error });
    }
};