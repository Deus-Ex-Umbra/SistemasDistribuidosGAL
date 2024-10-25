const { GraphQLObjectType, GraphQLString, GraphQLSchema, GraphQLList, GraphQLNonNull } = require('graphql');
const { Agenda } = require('./database');

const AgendaType = new GraphQLObjectType(
    {
        name: 'Agenda',
        fields: {
            id: {
                type: GraphQLString
            },
            ci: {
                type: GraphQLString
            },
            nombres: {
                type: GraphQLString
            },
            apellidos: {
                type: GraphQLString
            },
            celular: {
                type: GraphQLString
            },
            email: {
                type: GraphQLString
            },
            profesion_id: {
                type: GraphQLString
            }
        }
    }
)

const RootQuery = new GraphQLObjectType(
    {
        name: 'RootQueryType',
        fields: {
            agenda: {
                type: AgendaType,
                args: {
                    id: {
                        type: GraphQLString
                    }
                },
                resolve(parent, args) {
                    return Agenda.findById(args.id);
                }
            },
            agendas: {
                type: new GraphQLList(AgendaType),
                resolve(parent, args) {
                    return Agenda.find();
                }
            }
        }
    }
);

const Mutation = new GraphQLObjectType(
    {
        name: 'Mutation',
        fields: {
            addAgenda: {
                type: AgendaType,
                args: {
                    ci: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    nombres: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    apellidos: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    celular: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    email: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    profesion_id: {
                        type: new GraphQLNonNull(GraphQLString)
                    }
                },
                resolve(parent, args) {
                    let agenda = new Agenda({
                        ci: args.ci,
                        nombres: args.nombres,
                        apellidos: args.apellidos,
                        celular: args.celular,
                        email: args.email,
                        profesion_id: args.profesion_id
                    });
                    return agenda.save();
                }
            },

            updateAgenda: {
                type: AgendaType,
                args: {
                    id: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    ci: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    nombres: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    apellidos: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    celular: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    email: {
                        type: new GraphQLNonNull(GraphQLString)
                    },
                    profesion_id: {
                        type: new GraphQLNonNull(GraphQLString)
                    }
                },
                resolve(parent, args) {
                    return Agenda.findByIdAndUpdate(args.id, {
                        ci: args.ci,
                        nombres: args.nombres,
                        apellidos: args.apellidos,
                        celular: args.celular,
                        email: args.email,
                        profesion_id: args.profesion_id
                    });
                }
            },
        }
    }
);

module.exports = new GraphQLSchema(
    {
        query: RootQuery,
        mutation: Mutation
    }
);

