package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.dataaccesslayer.Databasedao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class DatabaseServicePostgres implements DatabaseService{

    @Resource
    Databasedao databaseDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<String> getColumns(String tableName) throws SQLException {

        return databaseDao.getColumnnames(tableName);
    }
}
