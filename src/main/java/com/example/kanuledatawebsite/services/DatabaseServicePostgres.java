package com.example.kanuledatawebsite.services;

import com.example.kanuledatawebsite.dataaccesslayer.Databasedao;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseServicePostgres implements DatabaseService{

    @Autowired
    @Setter(AccessLevel.PACKAGE)
    Databasedao databaseDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getColumns(String tableName) throws SQLException {

        return databaseDao.getColumnnames(tableName);
    }
}
