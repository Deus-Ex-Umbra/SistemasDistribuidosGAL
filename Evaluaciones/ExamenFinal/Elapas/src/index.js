import express from 'express';
import morgan from 'morgan';
import sequelize from './database/database.js';
import facturaRoutes from './routes/factura.routes.js'

const app = express();
app.use(morgan('dev'));
app.use(express.json());

app.use('/facturas', facturaRoutes);

const PORT = process.env.PORT || 3050;

app.get('/', (req, res) => {
    const message = 'Facturas';
    res.send(message);
});

const startServer = async () => {
    try {
        await sequelize.sync();
        app.listen(PORT, () => {
            console.log(`Servidor corriendo en: ${PORT}`);
        });
    } catch (error) {
        console.log('No conectado');
    }
}

startServer();