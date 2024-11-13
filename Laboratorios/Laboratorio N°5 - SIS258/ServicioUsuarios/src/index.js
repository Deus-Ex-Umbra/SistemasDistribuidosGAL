require('dotenv').config();
const express = require('express');
const conectarDB = require('../config/database');
const { conectarRabbitMQ } = require('./services/rabbitmq');

const app = express();

conectarDB();
conectarRabbitMQ();

app.use(express.json());

const UsuarioRoute = require('./routes/usuario.route');
app.use('/usuario', UsuarioRoute);

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Servidor corriendo en el puerto ${PORT}`);
});