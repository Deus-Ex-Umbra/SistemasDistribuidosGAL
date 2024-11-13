const mongoose = require('mongoose');

const conectarDB = async () => {
    try {
        await mongoose.connect(process.env.MONGODB_URL);
        console.log('Conexión con MongoDB establecida');
    } catch (error) {  
        console.log('Error al conectar con MongoDB');
        process.exit(1);
    }
};

module.exports = conectarDB;