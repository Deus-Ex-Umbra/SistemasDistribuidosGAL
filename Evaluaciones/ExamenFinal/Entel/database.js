const { Sequelize, DataTypes } = require('sequelize');

const sequelize = new Sequelize('bd_cessa', 'root', '', {
    host: 'localhost',
    dialect: 'mysql'
});

const Factura = sequelize.define('factura', {
    id: {
        type: Sequelize.INTEGER,
        autoIncrement: true,
        allowNull: false,
        primaryKey: true
    },
    ci: {
        type: Sequelize.INTEGER,
    },
    fecha: {
        type: Sequelize.DATE
    },
    total: {
        type: Sequelize.INTEGER
    },
    pagado: {
        type: Sequelize.BOOLEAN
    }
}, {
    tableName: 'facturas',
    timestamps: false,
});

module.exports = Factura;