package com.example.kanuledatawebsite.dataaccesslayer;

import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * {@inheritDoc}
 */
@Repository
public class DatabasedaoPostgres implements Databasedao{


    DataSource dataSource;
    Connection con;
    DatabaseMetaData metaData;


    public DatabasedaoPostgres(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.con = dataSource.getConnection();
        this.metaData = this.con.getMetaData();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<String> getColumnnames(String tableName) throws SQLException {
        ArrayList<String> columnNames = new ArrayList<>();
        ResultSet resultSet = this.metaData.getColumns(null, null, tableName, null);
        while (resultSet.next()) {
            columnNames.add(resultSet.getString("COLUMN_NAME"));
        }
        this.con.close();
        return columnNames;
    }
}
