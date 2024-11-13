const mongoose = require('mongoose');
const bcrypt = require('bcrypt');

const usuarioSchema = new mongoose.Schema({
    nombre: {
        type: String
    },
    correo: {
        type: String,
        unique: true
    },
    password: {
        type: String
    }
});