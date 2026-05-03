package it.eg.sloth.dbmodeler.reader;

import it.eg.sloth.dbmodeler.model.database.DataBaseType;
import it.eg.sloth.dbmodeler.model.schema.Schema;
import it.eg.sloth.dbmodeler.model.schema.sequence.Sequence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Project: sloth-framework
 * Copyright (C) 2019-2025 Enrico Grillini
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 * <p>
 * Singleton per la gestione delle Scedulazioni
 *
 * @author Enrico Grillini
 */
public interface DbSchemaReader {

    //  String calcColumnType(DataRow dataRow);
//
//    <R> List<R> tablesData(Connection connection, String owner) throws FrameworkException, SQLException, IOException;
//
//    <R> List<R> constraintsData(Connection connection, String owner) throws FrameworkException, SQLException, IOException;
//
//    <R> List<R> indexesData(Connection connection, String owner) throws FrameworkException, SQLException, IOException;
//
//    <R> List<R> constantsData(Connection connection, String tableName, String keyName) throws FrameworkException, SQLException, IOException;

    List<Sequence> sequencesData() throws SQLException, IOException;
//
//    <R> List<R> viewsData(Connection connection, String owner) throws FrameworkException, SQLException, IOException;
//
//    <R> List<R> viewsColumnsData(Connection connection, String owner) throws FrameworkException, SQLException, IOException;
//
//    <R> List<R> storedProcedureData(Connection connection, String owner) throws FrameworkException, SQLException, IOException;
//
//    <R> List<R> statisticsData(Connection connection, String owner) throws FrameworkException, SQLException, IOException;
//
//    void addTables(Schema schema, Connection connection, String owner) throws SQLException, IOException, FrameworkException;
//
//    void addConstraints(Schema schema, Connection connection, String owner) throws SQLException, FrameworkException, IOException;
//
//    void addIndexes(Schema schema, Connection connection, String owner) throws SQLException, FrameworkException, IOException;
//
//    void addConstants(Schema schema, Connection connection) throws SQLException, FrameworkException, IOException;

    void addSequences(Schema schema) throws SQLException, IOException;

    //
//    void addViews(Schema schema, Connection connection, String owner) throws SQLException, IOException, FrameworkException;
//
//    void addStoredProcedure(Schema schema, Connection connection, String owner) throws SQLException, IOException, FrameworkException;
//
//    default Schema refreshSchemaByDbConnection(DbConnection dbConnection) throws SQLException, IOException, FrameworkException {
//        DriverManager.setLoginTimeout(1);
//        Connection connection = DriverManager.getConnection(dbConnection.getJdbcUrl(), dbConnection.getDbUser(), dbConnection.getDbPassword());
//
//        return refreshSchema(connection, dbConnection.getDbOwner());
//    }
//
    default Schema refreshSchema() throws SQLException, IOException {
        Schema schema = new Schema();

//        // Load Tables
//        addTables(schema, connection, owner);
//
//        // Load Tables - Constraints
//        addConstraints(schema, connection, owner);
//
//        // Load Tables - Indexes
//        addIndexes(schema, connection, owner);
//
//        // Load Tables - Constants
//        addConstants(schema, connection);

        // Carico i sequence
        addSequences(schema);

//        // Carico le viste
//        addViews(schema, connection, owner);
//
//        // Carico le stored procedure
//        addStoredProcedure(schema, connection, owner);

        return schema;
    }
//
//    Statistics refreshStatistics(Connection connection, String owner) throws SQLException, IOException, FrameworkException;
//
//    default String writeString(DataBase dataBase) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writer(new DefaultPrettyPrinter());
//
//        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataBase);
//    }

    class Factory {
        private Factory() {
            // NOP
        }

//        public static DbSchemaReader getDbSchemaReader(Connection connection) throws SQLException {
//            Class<?> driverClass = DriverManager.getDriver(connection.getMetaData().getURL()).getClass();
//
//            if (driverClass.getName().toLowerCase().contains("h2")) {
//                return DbSchemaReader.Factory.getDbSchemaReader(DataBaseType.H2);
//            } else if (driverClass.getName().toLowerCase().contains("oracle")) {
//                return DbSchemaReader.Factory.getDbSchemaReader(DataBaseType.ORACLE);
//            } else if (driverClass.getName().toLowerCase().contains("postgresql")) {
//                return DbSchemaReader.Factory.getDbSchemaReader(DataBaseType.POSTGRES);
//            } else {
//                return null;
//            }
//        }

        public static DbSchemaReader getDbSchemaReader(DataBaseType dataBaseType, String jdbcUrl, String username, String password, String owner) {
            // Imposto il reader corretto
            switch (dataBaseType) {
//                case H2:
//                    return new H2SchemaReader();
//                case ORACLE:
//                    return new OracleSchemaReader();
                case POSTGRES:
                    return new PostgresSchemaReader(jdbcUrl, username, password, owner);
                default:
                    return null;
            }
        }
    }
}
