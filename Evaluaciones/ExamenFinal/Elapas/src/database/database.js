import { Sequelize } from "sequelize";

const sequelize = new Sequelize("bd_cessa", "root", "", {
    host: "localhost",
    dialect: "mysql",
    logging: false,
});

export default sequelize;
