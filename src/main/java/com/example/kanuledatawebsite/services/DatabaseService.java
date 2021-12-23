package com.example.kanuledatawebsite.services;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DatabaseService {

    ArrayList<String> getColumns(String tableName) throws SQLException;
}
