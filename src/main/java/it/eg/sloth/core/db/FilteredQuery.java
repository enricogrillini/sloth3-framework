package it.eg.sloth.core.db;

import it.eg.sloth.core.base.ObjectUtil;
import it.eg.sloth.core.db.filter.BaseFilter;
import it.eg.sloth.core.db.filter.Filter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

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
@ToString(callSuper = true)
@EqualsAndHashCode
public class FilteredQuery extends SelectAbstractQuery {

    public static final String PLACEHOLDER = "/*W*/";

    protected List<Filter> filterList;

    public FilteredQuery(JdbcTemplate jdbcTemplate, String sqlDocumentsTable) {
        super(jdbcTemplate, sqlDocumentsTable);
        this.filterList = new ArrayList<>();
    }

    public FilteredQuery addBaseFilter(String sql, Object value) {
        filterList.add(new BaseFilter(sql, value));
        return this;
    }

    @Override
    protected String elabStatement() {
        StringBuilder sqlFilter = new StringBuilder();
        for (Filter filtro : filterList) {
            String statement = filtro.getWhereCondition();
            if (!ObjectUtil.isEmpty(statement)) {
                sqlFilter.append(sqlFilter.length() == 0 ? statement : " And " + statement);
            }
        }

        if (sqlFilter.length() != 0) {
            if (getStatement().toLowerCase().contains("where")) {
                sqlFilter.insert(0, " And ");
            } else {
                sqlFilter.insert(0, "Where ");
            }
        }

        return getStatement().replace(PLACEHOLDER, sqlFilter.toString());
    }

    @Override
    protected Object[] values() {
        List<Object> list = new ArrayList<>();
        for (Filter filtro : filterList) {
            list.addAll(filtro.values());
        }
        return list.toArray();
    }


}
