const amqplib = require('amqplib');

let canal, conexion;

const conectarRabbitMQ = async () => {
    try {
        conexion = await amqplib.connect('amqp://localhost');
        canal = await conexion.createChannel();
        await canal.assertQueue('mensajes');
        console.log('Conexión con RabbitMQ establecida');
    } catch (error) {
        console.log('Error al conectar con RabbitMQ');
    }
};

const enviarMensaje = async (mensaje) => {
    try {
        await canal.sendToQueue('mensajes', Buffer.from(mensaje));
    } catch (error) {
        console.log('Error al enviar mensaje a RabbitMQ');
    }
};

module.exports = { conectarRabbitMQ, enviarMensaje };