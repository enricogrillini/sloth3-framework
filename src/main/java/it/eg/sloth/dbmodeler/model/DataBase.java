package it.eg.sloth.dbmodeler.model;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import it.eg.sloth.dbmodeler.model.connection.DbConnection;
import it.eg.sloth.dbmodeler.model.schema.Schema;
import it.eg.sloth.dbmodeler.reader.DbSchemaReader;
import it.eg.sloth.spring.config.BaseObjectMapperConfig;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
@Data
public class DataBase implements JsonInterface {

    DbConnection dbConnection;
    Schema schema;

    public void refreshSchema() {
        DbSchemaReader dbSchemaReader = DbSchemaReader.Factory.getDbSchemaReader(dbConnection);
        //this.setSchema(dbSchemaReader.refreshSchemaByDbConnection(this.getDbConnection()));
    }

    public void readJson(File file) throws IOException {
        JsonMapper mapper = BaseObjectMapperConfig.defaultJsonMapper();
        DataBase dataBase = mapper.readValue(FileUtils.readFileToString(file, StandardCharsets.UTF_8), DataBase.class);

        dbConnection = dataBase.getDbConnection();
        schema = dataBase.getSchema();
    }

    public void readYaml(File file) throws IOException {
        YAMLMapper mapper = BaseObjectMapperConfig.defaultYamlMapper();
        DataBase dataBase = mapper.readValue(FileUtils.readFileToString(file, StandardCharsets.UTF_8), DataBase.class);

        dbConnection = dataBase.getDbConnection();
        schema = dataBase.getSchema();
    }


}
