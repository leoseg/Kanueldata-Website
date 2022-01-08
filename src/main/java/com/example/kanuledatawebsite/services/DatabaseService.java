package com.example.kanuledatawebsite.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DatabaseService {
    /**
     * Get column names from table
     * @param tableName name of table
     * @return list of column anmes
     * @throws SQLException sqlexception cause database access
     */
    List<String> getColumns(String tableName) throws SQLException;
}
