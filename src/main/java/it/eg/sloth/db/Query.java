package it.eg.sloth.db;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class Query {

    private String sql;

    private List<String> filters;
    private MapSqlParameterSource parameters;

    public Query(String sql) {
        this.sql = sql;
        this.filters = new ArrayList<>();
        this.parameters = new MapSqlParameterSource();
    }

    public Query addFilter(String sql) {
        filters.add(sql);

        return this;
    }

    public Query addParameter(String name, Object value) {
        parameters.addValue(name, value);

        return this;
    }

    public Query addFilterAndParam(String sql, String name, Object value) {
        if (!isEmpty(value)) {
            addFilter(sql);
            addParameter(name, value);
        }
        return this;
    }

    public <T> List<T> select(NamedParameterJdbcTemplate jdbcTemplate, Class<T> mappedClass) throws DataAccessException {
        return jdbcTemplate.query(getSql(), parameters, new BeanPropertyRowMapper<>(mappedClass));
    }

    public <T> T selectRow(NamedParameterJdbcTemplate jdbcTemplate, Class<T> mappedClass) {
        return jdbcTemplate.queryForObject(getSql(), parameters, new BeanPropertyRowMapper<>(mappedClass));
    }

    public int execute(NamedParameterJdbcTemplate jdbcTemplate) {
        return jdbcTemplate.update(getSql(), parameters);
    }

    private String getSql() {
        String whereCondition = String.join(" And\n", filters);

        if (!filters.isEmpty() && !sql.toLowerCase().contains("where")) {
            whereCondition = "Where " + whereCondition;
        }

        return sql.replace("/*W*/", whereCondition);
    }

    private boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String s) {
            return StringUtils.isEmpty(s);
        } else {
            return false;
        }
    }

    public String toString() {
        return "Query: %s\nParams:%s".formatted(getSql(), parameters);
    }
}