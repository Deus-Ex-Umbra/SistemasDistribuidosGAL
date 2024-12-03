const { GraphQLObjectType, GraphQLString, GraphQLSchema, GraphQLList, GraphQLNonNull } = require('graphql');
const Factura = require('./database.js');

const FacturaType = new GraphQLObjectType({
    name: 'Factura',
    fields: () => ({
        id: { type: GraphQLString },
        ci: { type: GraphQLString },
        fecha: { type: GraphQLString },
        total: { type: GraphQLString },
        pagado: { type: GraphQLString }
    })
});

const RootQuery = new GraphQLObjectType({
    name: 'RootQueryType',
    fields: {
        obtenerFactura: {
            type: FacturaType,
            args: { ci: { type: new GraphQLNonNull(GraphQLString) } },
            async resolve(parent, args) {
                const factura = await Factura.findOne({ where: { ci: args.ci } });
                if (!factura) {
                    throw new Error('No se encontró la factura');
                }
                return factura;
            }
        }
    }
});

const Mutation = new GraphQLObjectType({
    name: 'Mutation',
    fields: {
        editarFactura: {
            type: FacturaType,
            args: {
                ci: { type: new GraphQLNonNull(GraphQLString) },
                fecha: { type: GraphQLString },
                total: { type: GraphQLString },
                pagado: { type: GraphQLString }
            },
            async resolve(parent, args) {
                const factura = await Factura.findOne({ where: { ci: args.ci } });
                if (!factura) {
                    throw new Error('No se encontró la factura');
                }
                await factura.update({
                    fecha: args.fecha,
                    total: args.total,
                    pagado: args.pagado
                });
                return factura;
            }
        },
        editarEstadoFactura: {
            type: FacturaType,
            args: {
                ci: { type: new GraphQLNonNull(GraphQLString) },
                pagado: { type: new GraphQLNonNull(GraphQLString) }
            },
            async resolve(parent, args) {
                const factura = await Factura.findOne({ where: { ci: args.ci } });
                if (!factura) {
                    throw new Error('No se encontró la factura');
                }
                await factura.update({ pagado: args.pagado });
                return factura;
            }
        }
    }
});

module.exports = new GraphQLSchema({
    query: RootQuery,
    mutation: Mutation
});
