package com.example.kanuledatawebsite.services;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DatabaseService {
    /**
     * Get column names from table
     * @param tableName name of table
     * @return list of column anmes
     * @throws SQLException sqlexception cause database access
     */
    ArrayList<String> getColumns(String tableName) throws SQLException;
}
