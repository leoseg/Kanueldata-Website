package com.example.kanuledatawebsite.daotests;


import com.example.kanuledatawebsite.dataaccesslayer.DatabasedaoPostgres;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;


import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DatabaseDaoTests {


    DatabasedaoPostgres dbDao;

    @Autowired
    DataSource dataSource;

    @BeforeEach
    public void setup() throws SQLException {
        dbDao= new DatabasedaoPostgres(dataSource);

    }
//    @Sql(statements = "CREATE TABLE IF NOT EXISTS testtable " +
//            "(feature1 Char(22)," +
//            "feature2 Char(22)," +
//            "feature3 Char(22))"
//    )
    @Test
    public void testGetColumns() throws SQLException {
        ArrayList<String> exspectedColumnNames = new ArrayList<>(Arrays.asList("feature1","feature2","feature3"));
        var columNames=dbDao.getColumnnames("TESTTABLE");
        assertThat(columNames).isEqualTo(exspectedColumnNames);
    }
}
