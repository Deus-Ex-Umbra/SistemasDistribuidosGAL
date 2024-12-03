import { DataTypes } from 'sequelize';
import sequelize from '../database/database.js';

export const Factura = sequelize.define('facturas', 
    {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
        },
        ci: {
            type: DataTypes.STRING,
        },
        fecha: {
            type: DataTypes.DATE,
        },
        total: {
            type: DataTypes.DECIMAL,
        },
        pagado: {
            type: DataTypes.BOOLEAN,
        }
    }, {
        tableName: 'facturas',
        timestamps: false,
});