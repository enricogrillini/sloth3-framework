package it.eg.sloth.dbmodeler.reader;

import it.eg.sloth.dbmodeler.model.database.DataBaseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

@Disabled
class PostgresReaderRealTest extends AbstractReaderTest {

    private DbSchemaReader postgresSchemaReader;

    @BeforeEach
    void init() throws IOException, SQLException {
        postgresSchemaReader = DbSchemaReader.Factory.getDbSchemaReader(DataBaseType.POSTGRES, "jdbc:postgresql://localhost:5432/postgres", "gilda", "gilda", "gilda");

    }

    @Test
    void readSchemaTest() throws SQLException, IOException {
        postgresSchemaReader.refreshSchema();
    }

    @Test
    void readStatisticsTest() throws SQLException, IOException {
//        Statistics statistics = getDbSchemaReader().refreshStatistics(getConnection(), getOwner());
//
//        assertEquals("org.postgresql.Driver", statistics.getDriverClass());
//        assertEquals(13, statistics.getTableCount());
//        assertEquals(20, statistics.getIndexCount());
    }

}
