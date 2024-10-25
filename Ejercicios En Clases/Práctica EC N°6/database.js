const { Sequelize } = require('sequelize');
const sequelize = new Sequelize('db_agenda', 'root', '', {
    host: 'localhost',
    dialect: 'mysql'
});

//id, ci, nombres, apellidos, celular, email, profesion_id, created_at, updated_at

const Agenda = sequelize.define('agenda', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    ci: {
        type: Sequelize.STRING
    },
    nombres: {
        type: Sequelize.STRING
    },
    apellidos: {
        type: Sequelize.STRING
    },
    celular: {
        type: Sequelize.STRING
    },
    email: {
        type: Sequelize.STRING
    },
    profesion_id: {
        type: Sequelize.INTEGER
    }
}, {
    tableName: 'agenda',
    timestamps: false
});

sequelize.sync();

module.exports = { sequelize, Agenda };