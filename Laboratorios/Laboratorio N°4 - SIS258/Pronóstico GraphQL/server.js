const express = require('express');
const { graphqlHTTP } = require('express-graphql');
const schema = require('./schema');
const { sequelize } = require('./database');

const app = express();

app.use('/graphql', graphqlHTTP({
    schema,
    graphiql: true
}));

sequelize.authenticate()
    .then(() => {
        console.log('Conexión a la base de datos establecida');
        app.listen(4000, () => {
            console.log('Servidor corriendo en el puerto 4000');
        });
    })
    .catch(err => {
        console.error('No se pudo conectar a la base de datos:', err);
    });
