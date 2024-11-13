const { GraphQLObjectType, GraphQLString, GraphQLSchema, GraphQLList, GraphQLNonNull } = require('graphql');
const { Personas } = require('./database');

const PersonasType = new GraphQLObjectType({
  name: 'Personas',
  fields: {
    id: { type: GraphQLString },
    ci: { type: GraphQLString },
    nombres: { type: GraphQLString },
    primer_apellido: { type: GraphQLString },
    segundo_apellido: { type: GraphQLString },
  }
});

const RootQuery = new GraphQLObjectType({
  name: 'RootQueryType',
  fields: {
    personas: {
      type: new GraphQLList(PersonasType),
      resolve(parent, args) {
        return Personas.findAll();
      }
    },
    persona: {
      type: PersonasType,
      args: { ci: { type: new GraphQLNonNull(GraphQLString) } },
      resolve(parent, args) {
        return Personas.findOne({
          where: {
            ci: args.ci
          }
        });
      }
    }
  }
});

const schema = new GraphQLSchema({
  query: RootQuery
});

module.exports = schema;
