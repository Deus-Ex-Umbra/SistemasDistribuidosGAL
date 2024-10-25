const express = require('express');
const { graphqlHTTP } = require('express-graphql');
const schema = require('./schema');
const { sequelize } = require('./database');

const app = express();

app.use('/graphql', graphqlHTTP({
    schema,
    graphiql: true
}));

sequelize.authenticate().then(() => {
    console.log('Conexión exitosa a la base de datos');
    app.listen(3000, () => {
        console.log('Servidor iniciado en puerto 3000');
    });
}).catch(err => {
    console.error('No se pudo conectar a la base de datos:', err);
});