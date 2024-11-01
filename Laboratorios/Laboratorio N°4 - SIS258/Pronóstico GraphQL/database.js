const { Sequelize } = require('sequelize');
const sequelize = new Sequelize ('bd_bcb', 'root', '', {
    host: 'localhost',
    dialect: 'mysql'
});

const Cotizacion = (sequelize) => {
    return sequelize.define('cotizacion', {
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
            notNull: true
        },
        fecha: {
            type: DataTypes.DATE
        },
        cotizacion: {
            type: DataTypes.DECIMAL(10, 2)
        },
        cotizacion_oficial: {
            type: DataTypes.DECIMAL
        }
    });
};

sequelize.sync();

module.exports = { sequelize, Cotizacion };