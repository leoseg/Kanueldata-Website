package com.example.kanuledatawebsite.dao;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
