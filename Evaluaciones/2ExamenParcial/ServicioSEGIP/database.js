const { Sequelize, DataTypes } = require('sequelize');

const sequelize = new Sequelize('bd_segip', 'root', '', {
  host: 'localhost',
  dialect: 'mysql'
});

const Personas = sequelize.define('Personas', {
  id: {
    type: DataTypes.BIGINT,
    allowNull: false,
    primaryKey: true,
    autoIncrement: true
  },
  ci: {
    type: DataTypes.BIGINT,
    allowNull: false
  },
  nombres: {
    type: DataTypes.STRING(256),
    allowNull: false
  },
  primer_apellido: {
    type: DataTypes.STRING(64),
    allowNull: true
  },
  segundo_apellido: {
    type: DataTypes.STRING(64),
    allowNull: false
  }
}, {
    tableName: 'Personas',
    timestamps: true,
    createdAt: 'createdAt',
    updatedAt: 'updatedAt'
  }
);

module.exports = { Personas, sequelize };
