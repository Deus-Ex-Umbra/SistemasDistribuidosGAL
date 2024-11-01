const { GraphQLSchema, GraphQLString, GraphQLList, GraphQLNonNull, GraphQLObjectType } = require('graphql');
const { Cotizacion } = require('./database');

const CotizacionType = new GraphQLObjectType({
    name: 'Cotizacion',
    fields: () => ({
        id: { type: GraphQLString },
        fecha: { type: GraphQLString },
        cotizacion: { type: GraphQLString },
        cotizacion_oficial: { type: GraphQLString }
    })
});

const RootQuery = new GraphQLObjectType({
    name: 'RootQueryType',
    fields: {
        cotizaciones: {
            type: new GraphQLList(CotizacionType),
            resolve(parent, args) {
                return CotizacionType.findAll();
            }
        },
        cotizacion: {
            type: CotizacionType,
            args: { id: { type: GraphQLString } },
            resolve(parent, args) {
                return CotizacionType.findByPk(args.id);
            }
        },
        cotizacionByFecha: {
            type: CotizacionType,
            args: { fecha: { type: GraphQLString } },
            resolve(parent, args) {
                return CotizacionType.findAll({
                    where: {
                        fecha: args.fecha
                    }
                });
            }
        }
    }
});

const Mutation = new GraphQLObjectType({
    name: 'Mutation',
    fields: {
        addCotizacion: {
            type: CotizacionType,
            args: {
                fecha: { type: new GraphQLNonNull(GraphQLString) },
                cotizacion: { type: new GraphQLNonNull(GraphQLString) },
                cotizacion_oficial: { type: new GraphQLNonNull(GraphQLString) }
            },
            resolve(parent, args) {
                return CotizacionType.create({
                    fecha: args.fecha,
                    cotizacion: args.cotizacion,
                    cotizacion_oficial: args.cotizacion_oficial
                });
            }
        },
        updateCotizacion: {
            type: CotizacionType,
            args: {
                id: { type: new GraphQLNonNull(GraphQLString) },
                fecha: { type: new GraphQLNonNull(GraphQLString) },
                cotizacion: { type: new GraphQLNonNull(GraphQLString) },
                cotizacion_oficial: { type: new GraphQLNonNull(GraphQLString) }
            },
            resolve(parent, args) {
                return CotizacionType.update({
                    fecha: args.fecha,
                    cotizacion: args.cotizacion,
                    cotizacion_oficial: args.cotizacion_oficial
                }, {
                    where: {
                        id: args.id
                    }
                });
            }
        },
        deleteCotizacion: {
            type: CotizacionType,
            args: {
                id: { type: new GraphQLNonNull(GraphQLString) }
            },
            resolve(parent, args) {
                return CotizacionType.destroy({
                    where: {
                        id: args.id
                    }
                });
            }
        }
    }
});

module.exports = new GraphQLSchema({
    query: RootQuery,
    mutation: Mutation
});
