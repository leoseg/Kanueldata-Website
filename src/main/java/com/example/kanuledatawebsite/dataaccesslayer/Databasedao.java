package com.example.kanuledatawebsite.dataaccesslayer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Databasedao {

    ArrayList<String> getColumnnames(String tableName) throws SQLException;

}
