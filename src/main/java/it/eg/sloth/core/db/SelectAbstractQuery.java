package it.eg.sloth.core.db;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Project: sloth3-framework
 * Copyright (C) 2022-2025 Enrico Grillini
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * <p>
 *
 * @author Enrico Grillini
 */
@Slf4j
@Data
public abstract class SelectAbstractQuery implements SelectQueryInterface {

    private String statement;
    private JdbcTemplate jdbcTemplate;

    protected abstract String elabStatement();

    protected abstract Object[] values();

    public SelectAbstractQuery(JdbcTemplate jdbcTemplate, String statement) {
        this.jdbcTemplate = jdbcTemplate;
        this.statement = statement;
    }

    public <T> List<T> selectTable(Class<T> classType) {
        return getJdbcTemplate().query(elabStatement(), new BeanPropertyRowMapper<>(classType), values());
    }

    public <T> T selectRow(Class<T> classType) {
        return getJdbcTemplate().queryForObject(elabStatement(), new BeanPropertyRowMapper<>(classType), values());
    }

    public List<Map<String, Object>> selectTable() {
        return getJdbcTemplate().query(elabStatement(), new ColumnMapRowMapper(), values());
    }

    public Map<String, Object> selectRow() {
        return getJdbcTemplate().queryForObject(elabStatement(), new ColumnMapRowMapper(), values());
    }

}
