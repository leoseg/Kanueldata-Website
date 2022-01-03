package com.example.kanuledatawebsite.dataaccesslayer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * interface for common functions of the database
 */
public interface Databasedao {
    /**
     * Gets the columnames of a table
     * @param tableName Name of the table
     * @return  List with columnames
     * @throws SQLException sqlexception cause database access
     */
    ArrayList<String> getColumnnames(String tableName) throws SQLException;

}
